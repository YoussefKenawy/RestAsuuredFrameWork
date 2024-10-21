package DealApp.MyAccount;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetMyProfile extends RestAssuredUtilities {
    @Test(dependsOnMethods = {"DealApp.MyAccount.Rea.getOTP","DealApp.MyAccount.Rea.reaRequestOTP", "DealApp.MyAccount.Rea.reaRegister", "DealApp.MyAccount.Rea.reaEnterOTP","DealApp.MyAccount.Rea.authorizeWithNafaz","DealApp.MyAccount.DeleteAccount.deleteReaAccount"})
    public void getMyProfile() throws InterruptedException {
        Thread.sleep(10000);
        String endpoint="/user/profile";
        Response response=performGet(endpoint,Rea.reaToken);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.jsonPath().getBoolean("data.isVerifiedByNafath"),"nafaz should be true");





    }
}
