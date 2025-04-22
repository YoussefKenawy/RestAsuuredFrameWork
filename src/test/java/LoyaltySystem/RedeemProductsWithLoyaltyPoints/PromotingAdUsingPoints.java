package LoyaltySystem.RedeemProductsWithLoyaltyPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;
import static DealApp.ADS.CreateAd.adId;
import static DealApp.AdsLicense.CreateLicense.licenseId;
public class PromotingAdUsingPoints extends RestAssuredUtilities
    {

    @Test(priority = 1,dependsOnMethods = "DealApp.ADS.CreateAd.createAdBySavedRea")
    public void approveAdByAdmin()
        {
            String endpoint = "/ad/" + adId + "/status";
            Map<String, Object> requestBody = Map.of("status", "APPROVED");
            Response response = performPut(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(),200);
        }
    @Test(priority = 2, dependsOnMethods = {"approveAdByAdmin","DealApp.ADS.CreateAd.createAdBySavedRea", "DealApp.AdsLicense.CreateLicense.publishAdToAdmin",} )
    public void redeemPointForPromotingAd()
        {
            String endpoint = "/ad/" + adId + "/upgrade";
            Map<String, Object> requestBody = Map.of("paymentMethod", "LOYALTY_CREDIT");
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(),200);
        }

    }
