package DealApp.MyAccount.REA;
import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import static utilities.RestAssuredUtilities.performGet;

public class GetMyProfileDataAsRea extends BaseTest
    {

    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.MyClients.getCommunicatedRequestsOfPhone",
            "DealApp.ADS.CreateAd.createAd",
            "DealApp.Requests.CreateRequest.createRequestByClient",
            "DealApp.Requests.InteractWithRequests.activateRequestsBySavedClient",
            "DealApp.Requests.InteractWithRequests.Check_CALL_PHONE_Interaction_WithRequestsByRea"
    })

    public void getMyProfile() throws InterruptedException {
        Thread.sleep(10000);
        String endpoint = "/user/profile";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("data.isVerifiedByNafath"), "Nafaz should be true");
        Assert.assertNotNull(response.jsonPath().getString("data.communicatedWithClients"),"num of client should not be 0");
        Assert.assertNotEquals(response.jsonPath().getInt("data.interactionsActivity.call"),"0","call communications should not be 0");
        Assert.assertNotEquals(response.jsonPath().getString("data.userActivites.adCounts"), "0","Ads should  not be 0");
        Assert.assertNotNull(response.jsonPath().getString("data.createdAt"), "Joining date should not be null");
        Assert.assertEquals(response.jsonPath().getString("data.role"), "REAL_STATE_AGENT");
    }
}
