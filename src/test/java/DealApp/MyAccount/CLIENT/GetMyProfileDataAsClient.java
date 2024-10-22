package DealApp.MyAccount.CLIENT;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

public class GetMyProfileDataAsClient extends RestAssuredUtilities {

    @Test(dependsOnMethods = {
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
    })
    public void getMyProfileAsClient() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint = "/user/profile";
        Response response = performGet(endpoint, Client.clientToken);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertFalse(response.jsonPath().getBoolean("data.isVerifiedByNafath"), "Nafaz should be false");
        Assert.assertEquals(response.jsonPath().getString("data.falAuthorizationStatus"), "NOT_VERIFIED", "Fal should be not verified");
        Assert.assertNotNull(response.jsonPath().getString("data.createdAt"), "Joining date should not be null");
        Assert.assertEquals(response.jsonPath().getString("data.role"), "CLIENT");
    }

}
