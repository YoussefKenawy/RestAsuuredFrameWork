package DealApp.AdsLicense;

import DealApp.ADS.CreateAd;
import DealApp.Subscriptions.SubscribeToPackage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateLicense extends RestAssuredUtilities {
    static String licenseId;
public static String uniqueCouponText;

public static Map<String, Object> createDynamicCoupon()
    {
        // Generate a unique text for the coupon
        uniqueCouponText = "Coupon" + UUID.randomUUID().toString().substring(0, 8);
        // Create the coupon JSON object
        Map<String, Object> coupon = Map.of(
                "text", uniqueCouponText,
                "products", new String[]{"AD_LICENSE_REQUEST_RENT","AD_LICENSE_REQUEST_SALE"},
                "roles", new String[]{"CLIENT", "REAL_STATE_AGENT"},
                "discountType", "PERCENTAGE",
                "discountValue", 100,
                "expirationDate", "2030-06-12T18:17:58.691Z"
        );
        return coupon;
    }

public static void createCoupon()
    {
        String endpoint = "/coupon";
        Response response = performPost(endpoint, Tokens.getInstance().getAdminToken(), createDynamicCoupon(), sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);

    }
    @Test(dependsOnMethods ="DealApp.ADS.CreateAd.createAdBySavedRea")
    public void addLicense()
    {
        String endpoint = "/ad-license-request";
        Map<String, Object> requestBody = Map.of("adId", CreateAd.adId);
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("createdAt"), "Created At must not be bull");
        licenseId = response.jsonPath().getString("_id");
        Assert.assertNotNull(licenseId, "License ID  must not be bull");
        System.out.println(" ****************************Your ID IS ********************8:" +licenseId);
    }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea","addLicense"})
    public void upgradeLicense()
    {
        createCoupon();
        System.out.println(" ****************************Your ID IS ********************8:" +licenseId);
        String endpoint = "/ad-license-request/" + licenseId + "/upgrade";
        Map<String, Object> requestBody = Map.of("coupon",uniqueCouponText);
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "MISSING_INFO");
        Assert.assertNotNull(response.jsonPath().getString("createdAt"), "Created At must not be bull");
        Assert.assertNotNull(response.jsonPath().getString("_id"), "License Id At must not be bull");
    }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea", "upgradeLicense","addLicense"})
    public void addLicenseForOwner() throws IOException
    {
        String endpoint = "/ad-license-request/" + licenseId;
        Map<String, Object> requestBody = getJsonDataAsMap("/AdsLisence/addLicenseForOwner.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "IN_PROGRESS");
        Assert.assertNotNull(response.jsonPath().getString("advertiserRelation"), "OWNER");
    }
    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea", "upgradeLicense","addLicense"})
    public void addLicenseForAttorney() throws IOException
    {
        String endpoint = "/ad-license-request/" + licenseId;
        Map<String, Object> requestBody = getJsonDataAsMap("/AdsLisence/addLicenseForAttorney.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "IN_PROGRESS");
        Assert.assertNotNull(response.jsonPath().getString("advertiserRelation"), "ATTORNEY");
    }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea", "upgradeLicense","addLicense"})
    public void addLicenseForEntity() throws IOException
    {
        String endpoint = "/ad-license-request/" + licenseId;
        Map<String, Object> requestBody = getJsonDataAsMap("/AdsLisence/addLicenseForEntity.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "IN_PROGRESS");
        Assert.assertNotNull(response.jsonPath().getString("advertiserRelation"), "ENTITY");
    }
}


