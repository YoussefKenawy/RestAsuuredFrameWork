package DealApp.Subscriptions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class CancelAutoRenewal extends RestAssuredUtilities
{
    @Test(dependsOnMethods = {"DealApp.Subscriptions.SubscribeToPackage.subscribeToPackageWithNoCoupon","DealApp.Subscriptions.SubscribeToPackage.calculatePriceWithoutCoupon","DealApp.Subscriptions.SubscribeToPackage.getCard"})
    public void cancelAutoRenewal()
    {
        Map<String, Object> requestBody = Map.of("autoRenewal", false);
        String endpoint = "/subscribe-package/"+SubscribeToPackage.subscriptionPackageId;
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
