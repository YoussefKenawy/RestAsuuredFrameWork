package DealApp;

import DealApp.MyAccount.CLIENT.Client;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.Tokens;

import java.io.IOException;

import static DealApp.MyAccount.CLIENT.Client.clientId;
import static DealApp.MyAccount.REA.Rea.reaId;
import static utilities.RestAssuredUtilities.performDelete;

public class BaseTest
    {
    private Rea reaInstance;
    private Client clientInstance;
    public static String newReaToken;
    public static String newClientToken;


    @BeforeSuite
    public void intializeReaAndClientAccounts() throws IOException, InterruptedException
        {
            reaInstance = new Rea();
            reaInstance.reaRegister();
            reaInstance.reaRequestOTP();
            reaInstance.getOTP();
            newReaToken = reaInstance.reaEnterOTP();
            reaInstance.authorizeWithNafaz();

            clientInstance = new Client();
            clientInstance.clientRegister();
            clientInstance.clientRequestOTP();
            clientInstance.getOTP();
            newClientToken = clientInstance.clientEnterOTP();


        }

    public void reaTearDown()
        {
            String endpoint = "/user/" + reaId;
            Response response = performDelete(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.statusCode(), 200);
        }

    public void clientTearDown()
        {
            String endpoint = "/user/" + clientId;
            Response response = performDelete(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.statusCode(), 200);
        }

    @AfterSuite
    public void accountsTearDown()
        {
            if (reaInstance != null && Rea.isReaCreated != null)
                {
                    reaTearDown();
                }


            if (clientInstance != null && Client.isClientCreated != null)
                {
                    clientTearDown();
                }

        }

    }


