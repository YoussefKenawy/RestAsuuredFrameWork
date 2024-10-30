package DealApp.Requests;

import DealApp.MyAccount.CLIENT.Client;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

import java.io.IOException;
import java.util.Map;

public class CreateRequest extends RestAssuredUtilities {
    public static String requestIdByRea;
    public static String requestIdByClient;
    @Test
    public void createRequestBySavedRea() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdByRea = response.jsonPath().getString("data._id");
        Assert.assertNotNull((requestIdByRea),"id should not be null");
    }
@Test (dependsOnMethods = {
        "DealApp.MyAccount.REA.Rea.reaRegister",
        "DealApp.MyAccount.REA.Rea.reaRequestOTP",
        "DealApp.MyAccount.REA.Rea.getOTP",
        "DealApp.MyAccount.REA.Rea.reaEnterOTP",
})
public void createRequestsByNewRea() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Rea.reaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdByRea = response.jsonPath().getString("data._id");
        Assert.assertNotNull((requestIdByRea),"id should not be null");
    }
    @Test
    public void createRequestByClient() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdByClient = response.jsonPath().getString("data._id");
        Assert.assertNotNull(requestIdByClient, "id should not be null");
    }
    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
    })
    public void createRequestByNewClient() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Client.clientToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdByClient = response.jsonPath().getString("data._id");
        Assert.assertNotNull(requestIdByClient, "id should not be null");
    }

}
