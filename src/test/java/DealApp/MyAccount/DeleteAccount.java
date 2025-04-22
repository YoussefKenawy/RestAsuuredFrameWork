/*
package DealApp.MyAccount;

import DealApp.MyAccount.CLIENT.Client;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.RestAssuredUtilities;

public class DeleteAccount extends RestAssuredUtilities
    {

    @Test(dependsOnMethods = {  "DealApp.MyAccount.CLIENT.", "DealApp.MyAccount.CLIENT.GetMyProfileDataAsClient.getMyProfileAsClient"})
    public void deleteClientAccount()
        {
            if (Client.clientToken != null& Client.isClientCreated)
                {
                    {
                        String endpoint = "/user/profile";
                        Response response = performDelete(endpoint, Client.clientToken);
                        Assert.assertEquals(response.statusCode(), 200);
                    }
                }

        }

    @Test(dependsOnMethods = {"DealApp.MyAccount.REA.Rea.reaEnterOTP", "DealApp.MyAccount.REA.Rea.getOTP", "DealApp.MyAccount.REA.Rea.reaRequestOTP", "DealApp.MyAccount.REA.Rea.reaRegister"})
    public void deleteReaAccount()
        {
            if (Client.clientToken != null & Rea.isReaCreated)
                {
                    String endpoint = "/user/profile";
                    Response response = performDelete(endpoint, Rea.reaToken);
                    Assert.assertEquals(response.statusCode(), 200);
                }
        }

    }

*/
