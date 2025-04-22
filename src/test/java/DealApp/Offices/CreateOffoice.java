package DealApp.Offices;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static DealApp.MyAccount.REA.Rea.reaToken;
import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.*;

public class CreateOffoice extends BaseTest
    {
        public  static String EntityLicenseId;
    @Test(priority = 1)
    public void authorizeWithFalAsEntity() throws IOException, InterruptedException
        {
            Thread.sleep(10000);
            String endpoint = "/fal-licenses";

            Map<String, Object> requestBody = Map.of("imageUrl", "imageUrl:https://uploadsstaging.dealapp.sa/daf8c0dd-1b76-4502-9f08-fa3f35317725.webp", "crNumberUrl", "https://uploadsstaging.dealapp.sa/47cad0d8-dbd6-4fa3-b750-ae44b03a15af-n.webp", "type", "ENTITY");
            Response response = performPost(endpoint, reaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("status"), "PENDING");
            EntityLicenseId=response.jsonPath().getString("_id");
        }

    @Test(dependsOnMethods = "authorizeWithFalAsEntity",priority = 2)
    public void acceptLicenseByAdmin()
        {
            String endpoint = "/fal-licenses/admin/" + EntityLicenseId;
            Map<String, Object> requestBody = Map.of("status", "APPROVED");
            Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }

    @Test(priority = 3)
    public void createOfficeByNewRea() throws IOException
        {
            String endpoint = "/offices/";
            Map<String, Object> requestBody = getJsonDataAsMap("/Offices/createOffice.json");
            Response response = performPost(endpoint, reaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
        }
    @Test(priority = 4)
    public void checkReaCanOnlyCreateOneOffice() throws IOException
        {
            String endpoint = "/offices/";
            Map<String, Object> requestBody = getJsonDataAsMap("/Offices/createOffice.json");
            Response response = performPost(endpoint, Tokens.getInstance().getReaToken(),requestBody, sendHeaders());
            Assert.assertEquals(response.jsonPath().getString("error.code"),"error.realEstateOffice.onlyAgentTypeEntityCanAuthorizeTheirOffice" );
        }
    @Test(priority = 5)
    public void checkVerifiedEntityFalReaCanCreateOneOffice() throws IOException, InterruptedException
        {
            Thread.sleep(3000);
            String endpoint = "/offices/";
            Map<String, Object> requestBody = getJsonDataAsMap("/Offices/createOffice.json");
            Response response = performPost(endpoint, reaToken,requestBody, sendHeaders());
            Assert.assertEquals(response.jsonPath().getString("error.code"),"error.realEstateOffice.agentHasAlreadyAnOfficeAuthorizeRequest" );
        }

    }
