package DealApp.MyAccount;
import DealApp.MyAccount.CLIENT.Client;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

public class DeleteAccount extends RestAssuredUtilities
{
    @Test (dependsOnMethods = {"DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
    "DealApp.MyAccount.CLIENT.GetMyProfileDataAsClient.getMyProfileAsClient"})
    public  void deleteClientAccount()

    {
        String endpoint="/user/profile";
        Response response=performDelete(endpoint, Client.clientToken);
        Assert.assertEquals(response.statusCode(),200);
    }


    @Test (dependsOnMethods = {"DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.GetMyProfileDataAsRea.getMyProfile"})
    public  void deleteReaAccount()
    {
        String endpoint="/user/profile";
        Response response=performDelete(endpoint, Rea.reaToken);
        Assert.assertEquals(response.statusCode(),200);
    }
}
