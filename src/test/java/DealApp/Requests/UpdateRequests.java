package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class UpdateRequests extends RestAssuredUtilities {

    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestBySavedRea")
    public void UpdateRequestByRea() throws IOException
    {
        String endpoint = "/request/"+CreateRequest.requestIdBySavedRea+ "/update";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/UpdateRequests.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void UpdateRequestClient() throws IOException
    {
        String endpoint = "/request/"+CreateRequest.requestIdSavedClient+ "/update";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/UpdateRequests.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString(" updatedAt"));
        Assert.assertEquals(response.statusCode(), 200);
    }

}
