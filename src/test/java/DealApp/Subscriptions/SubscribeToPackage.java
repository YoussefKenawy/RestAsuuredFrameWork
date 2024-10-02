package DealApp.Subscriptions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;
import java.util.UUID;


public class SubscribeToPackage extends RestAssuredUtilities {
    public static String cardID;
    public static int finalPrice;
    public static String uniqueCouponText;
    public static String subscriptionPackageId;


    @Test
    public static void calculatePriceWithoutCoupon()
    {
        String endpoint = "/subscribe-package/calculate-price";
        Map<String, Object> requestBody = Map.of("autoRenewal", true, "packageName", "PROFESSIONAL_ONE_YEAR");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "PROFESSIONAL_ONE_YEAR");
        int percentage = response.jsonPath().getInt("autoRenewalDiscount.percentage");
        Assert.assertTrue(percentage != 0);
        finalPrice = response.jsonPath().getInt("finalPrice");
        Assert.assertTrue(finalPrice != 0);


    }

    @Test
    public static void getCard()
    {
        String endpoint = "/card";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
        cardID = response.jsonPath().getString("data[0]._id");
        Assert.assertNotNull(cardID, "ID should not be null");
        System.out.println("CARD ID IS :"+cardID);
    }

    public static Map<String, Object> createDynamicCoupon()
    {
        // Generate a unique text for the coupon
        uniqueCouponText = "Coupon" + UUID.randomUUID().toString().substring(0, 8);
        // Create the coupon JSON object
        Map<String, Object> coupon = Map.of(
                "text", uniqueCouponText,
                "products", new String[]{"PROFESSIONAL_ONE_MONTH", "PROFESSIONAL_ONE_YEAR"},
                "roles", new String[]{"CLIENT", "REAL_STATE_AGENT"},
                "discountType", "PERCENTAGE",
                "discountValue", 10,
                "expirationDate", "2030-06-12T18:17:58.691Z"
        );
        return coupon;
    }

    @Test
    public static void createCoupon()
    {
        String endpoint = "/coupon";
        Response response = performPost(endpoint, Tokens.getInstance().getAdminToken(), createDynamicCoupon(), sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);

    }

    @Test(dependsOnMethods = {"createCoupon", "getCard"})
    public static void calculatePriceWithCoupon()
    {
        System.out.println("-------------" + uniqueCouponText);
        String endpoint = "/subscribe-package/calculate-price";
        Map<String, Object> requestBody = Map.of("autoRenewal", true, "coupon", uniqueCouponText, "packageName", "PROFESSIONAL_ONE_YEAR");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "PROFESSIONAL_ONE_YEAR");
        int percentage = response.jsonPath().getInt("autoRenewalDiscount.percentage");
        Assert.assertTrue(percentage != 0);
        Assert.assertEquals(response.jsonPath().getInt("couponDiscount.value"), 10);
        Assert.assertEquals(response.jsonPath().getString("couponDiscount.coupon"), uniqueCouponText);

        finalPrice = response.jsonPath().getInt("finalPrice");
        Assert.assertTrue(finalPrice != 0);
    }

    @Test(dependsOnMethods = {"calculatePriceWithoutCoupon", "getCard"})
    // Ensure both calculatePrice and getCard run before this test
    public void subscribeToPackageWithNoCoupon()
    {
        Map<String, Object> requestBody = Map.of("autoRenewal", true, "cardId", cardID, "packageName", "PROFESSIONAL_ONE_YEAR");
        String endpoint = "/subscribe-package";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("data.payment.metadata.autoRenewal"));
        Assert.assertEquals(response.jsonPath().getString("data.payment.metadata.productName"), "PROFESSIONAL_ONE_YEAR");
        subscriptionPackageId = response.jsonPath().getString("data.subscription.packageId");
        Assert.assertNotNull(subscriptionPackageId, "ID should not be null");

    }

    @Test(dependsOnMethods = {"calculatePriceWithCoupon", "createCoupon","getCard"})
    public void subscribeToPackageWithCoupon()
    {
        Map<String, Object> requestBody = Map.of("autoRenewal", true,"cardId",cardID ,"coupon" ,uniqueCouponText, "packageName", "PROFESSIONAL_ONE_YEAR");
        String endpoint = "/subscribe-package";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("data.payment.metadata.autoRenewal"));
        Assert.assertEquals(response.jsonPath().getString("data.payment.metadata.productName"), "PROFESSIONAL_ONE_YEAR");
        Assert.assertEquals(response.jsonPath().getString("data.coupon.text"), uniqueCouponText);

    }
}
