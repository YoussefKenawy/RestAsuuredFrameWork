package DealApp.Requests;

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
    public void createRequestByRea() throws IOException
    {
        String endpoint = "/request";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/CreateRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
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

}
