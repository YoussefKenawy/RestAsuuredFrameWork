package DealApp.Requests;

import DealApp.MyAccount.CLIENT.Client;
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
                "paymentMethod", "LOYALTY_CREDIT",
                "requestType", "SERIOUS"
        );
        return requestBody;
    }


    @Test (dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea"})
    public void upgradeReaRequest() throws IOException
    {

        String endpoint = "/request/"+CreateRequest.requestIdBySavedRea+"/upgrade";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), sendRequestBodyForReaRequest(), sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString(" updatedAt"));
        Assert.assertEquals(response.statusCode(), 200);
    }


    public static Map<String, Object> sendRequestBodyForClientRequest()
    {
        Map<String, Object> requestBody = Map.of(
                "coupon", "Marwan",
                "requestType", "SERIOUS"
        );
        return requestBody;
    }
    @Test (dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequestsBySavedClient"})
    public void upgradeClientRequest() throws IOException
    {

        String endpoint = "/request/"+CreateRequest.requestIdSavedClient+"/upgrade";
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(), sendRequestBodyForClientRequest(), sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString(" updatedAt"));
        Assert.assertEquals(response.statusCode(), 200);
    }

}

