package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

public class UpgradeRequests extends RestAssuredUtilities {
    public static Map<String, Object> sendRequestBodyForReaRequest()
    {
        Map<String, Object> requestBody = Map.of(
                "cardId", "66f3c96214fc6e57c344aaac",
                "requestType", "SERIOUS"
        );
        return requestBody;
    }


    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByRea")
    public void upgradeReaRequest() throws IOException
    {

        String endpoint = "/request/"+CreateRequest.requestIdByRea+ "/upgrade";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), sendRequestBodyForReaRequest(), sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString(" updatedAt"));
        Assert.assertEquals(response.statusCode(), 200);
    }


    public static Map<String, Object> sendRequestBodyForClientRequest()
    {
        Map<String, Object> requestBody = Map.of(
                "cardId", "66f42e2612c3a072aa5a5c63",
                "requestType", "SERIOUS"
        );
        return requestBody;
    }
    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void upgradeClientRequest() throws IOException
    {

        String endpoint = "/request/"+CreateRequest.requestIdByClient+ "/upgrade";
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(), sendRequestBodyForClientRequest(), sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString(" updatedAt"));
        Assert.assertEquals(response.statusCode(), 200);
    }
}

