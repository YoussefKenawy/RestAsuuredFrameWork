package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

import java.io.IOException;
import java.util.Map;

public class InteractWithRequests extends RestAssuredUtilities {
    @Test
    public void Check_chat_Interaction_WithRequests()
    {
        String endpoint = "/request/66bd9757209005078880d88d/interaction";
        String requestBody = "{\"type\":\"CHAT\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }

    @Test (dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestByClient","activateRequests"})
    public void Check_WHATSAPP_Interaction_WithRequestsByRea() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint = "/request/"+CreateRequest.requestIdByClient+"/interaction";
        String requestBody = "{\"type\":\"WHATSAPP\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }

    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void Check_CALL_PHONE_Interaction_WithRequestsByRea()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByClient+"/interaction";
        String requestBody = "{\"type\":\"CALL_PHONE\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }



    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void Check_FAVORITE_Interaction_WithRequestsByRea()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByClient+"/interaction";
        String requestBody = "{\"type\":\"FAVORITE\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");
    }

    @Test (dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestByClient"})
    public void Check_READ_Interaction_WithRequests()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByClient+"/interaction";
        String requestBody = "{\"type\":\"READ\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }

    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByRea")
    public void Check_ACCEPT_Interaction_WithRequests()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByRea+ "/interaction";
        String requestBody = "{\"type\":\"ACCEPT\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }

    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void Check_Hide_Requests()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByClient+ "/interaction";
        String requestBody = "{\"type\":\"REJECT\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

    }

    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByRea")
    public void Check_Show_Requests()
    {
        String endpoint = "/request/"+CreateRequest.requestIdByRea+ "/interaction";
        String requestBody = "{\"type\":\"REJECT\"}";

        Response response = performDelete(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void Check_Report_Requests() throws IOException
    {
        String endpoint = "/report";
        Map<String, Object> requestBody = getJsonDataAsMap("/PropertyRequests/ReportRequest.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        //  Assert.assertEquals(response.getStatusCode(), 201);
        //  Assert.assertEquals(response.getStatusLine(), "Created");
        Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.report.exists");

    }

    @Test
    public void Check_Close_Requests()
    {
        String endpoint = "/request/66d979fc56ffa67b5d985dc7/status";
        String requestBody = "{\"status\":\"CLOSED_NOT_INTERESTED\"}";
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.notFound");

    }


    @Test (dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestByClient")
    public void activateRequests() throws InterruptedException {
        String endpoint = "/request/"+CreateRequest.requestIdByClient+"/activate";
        Map<String,Object> requestBody=Map.of ("status","ACTIVE");
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(),requestBody,sendHeaders());
        // Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.cantRefreshBeforeTwoDays");
    Assert.assertEquals(response.statusCode(),200);
    }


}
