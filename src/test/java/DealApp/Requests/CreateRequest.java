package DealApp.Requests;
import DealApp.BaseTest;
import LoyaltySystem.PrepareLoyaltySettings.setLoyltyActions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Tokens;

import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.performPost;
import static utilities.RestAssuredUtilities.sendHeaders;

import java.io.IOException;
import java.util.Map;

public class CreateRequest extends BaseTest
    {
    public static String requestIdByNewRea;
    public static String requestIdNewClient;
    public static String requestIdBySavedRea;
    public static String requestIdSavedClient;
    @Test
    public static void createRequestBySavedRea() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdBySavedRea = response.jsonPath().getString("data._id");
        Assert.assertNotNull((requestIdBySavedRea),"id should not be null");
    }
@Test
public void createRequestsByNewRea() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint,newReaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdByNewRea = response.jsonPath().getString("data._id");
        Assert.assertNotNull((requestIdByNewRea),"id should not be null");

    }
    @Test
    public void createRequestByClient() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdSavedClient = response.jsonPath().getString("data._id");
        Assert.assertNotNull(requestIdSavedClient, "id should not be null");
        System.out.println("Request id for saved client is : when from create request for saved client  Response "+requestIdSavedClient);

    }
    @Test
    public void createRequestByNewClient() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, newClientToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
        requestIdNewClient = response.jsonPath().getString("data._id");
        Assert.assertNotNull(requestIdNewClient, "id should not be null");
    }

}
