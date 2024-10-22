package DealApp.MyAccount;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

public class DeleteAccount extends RestAssuredUtilities
{
    @Test (dependsOnMethods = {"DealApp.MyAccount.Client.clientEnterOTP","DealApp.MyAccount.Client.getOTP","DealApp.MyAccount.Client.clientRequestOTP","DealApp.MyAccount.Client.clientRegister"})
    public  void deleteClientAccount()

    {
        String endpoint="/user/skip";
        Response response=performPost(endpoint,Client.clientToken,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("data.status"),"DEACTIVATED");
    }


    @Test (dependsOnMethods = {"DealApp.MyAccount.Rea.reaEnterOTP","DealApp.MyAccount.Rea.getOTP","DealApp.MyAccount.Rea.reaRequestOTP","DealApp.MyAccount.Rea.reaRegister"})
    public  void deleteReaAccount()
    {
        String endpoint="/user/profile";
        Response response=performDelete(endpoint,Rea.reaToken);
        Assert.assertEquals(response.statusCode(),200);
    }
}
