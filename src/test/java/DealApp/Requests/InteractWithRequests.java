package DealApp.Requests;
import DealApp.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Tokens;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static DealApp.Requests.CreateRequest.*;
import static utilities.RestAssuredUtilities.*;

public class InteractWithRequests extends BaseTest
    {
    @Test (dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestsByNewRea"})
    public void activateRequestsByNewRea() throws InterruptedException
        {
            String endpoint = "/request/" + requestIdByNewRea + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint,newReaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }
    @Test (dependsOnMethods ={"DealApp.Requests.CreateRequest.createRequestByNewClient"})
    public void activateRequestsByNewClient() throws InterruptedException
        {
            String endpoint = "/request/" + requestIdNewClient + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint, newClientToken, requestBody, sendHeaders());
            // Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.cantRefreshBeforeTwoDays");
            Assert.assertEquals(response.statusCode(), 200);
        }
    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByClient",})
    public void activateRequestsBySavedClient() throws InterruptedException
        {
            String endpoint = "/request/" + requestIdSavedClient + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint,Tokens.getInstance().getClientToken(),requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }
       @Test (dependsOnMethods ={"DealApp.Requests.CreateRequest.createRequestBySavedRea",})
    public void activateRequestsBySavedRea() throws InterruptedException
        {
            String endpoint = "/request/" + CreateRequest.requestIdBySavedRea + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }


    @Test(dependsOnMethods = {
            "DealApp.Requests.CreateRequest.createRequestBySavedRea",
            "activateRequestsBySavedRea",

    })
    public void Check_chat_Interaction_WithRequests()
        {
            String endpoint = "/request/" +requestIdBySavedRea + "/communication-interaction";
            String requestBody = "{\"type\":\"CHAT\"}";
            Response response = performPatch(endpoint,newReaToken, requestBody, sendHeaders());
            try
                {
                  Assert.assertEquals(response.statusCode(),200);
                }
            catch (Exception e)
                {
                    Assert.assertEquals(response.jsonPath().getString("error.code"), "error.falLicense.notVerified");
                }
        }
    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_WHATSAPP_Interaction_WithRequestsByRea() throws InterruptedException
        {
            Thread.sleep(2000);
            String endpoint = "/request/" + requestIdNewClient + "/interaction";
            String requestBody = "{\"type\":\"WHATSAPP\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");
            System.out.println("Request id for saved client is : when before interact with whatsApp Response "+requestIdSavedClient);

        }

    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_CALL_PHONE_Interaction_WithRequestsByRea() throws InterruptedException
        {
            Thread.sleep(10000);
            String endpoint = "/request/" + requestIdNewClient + "/interaction";
            String requestBody = "{\"type\":\"CALL_PHONE\"}";
            Response response = performPatch(endpoint,Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_FAVORITE_Interaction_WithRequestsByRea()
        {
            String endpoint = "/request/" + requestIdNewClient + "/interaction";
            String requestBody = "{\"type\":\"FAVORITE\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");
        }

    @Test(dependsOnMethods = {
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"

    })
    public void Check_READ_Interaction_WithRequests()
        {
            String endpoint = "/request/" + requestIdNewClient + "/interaction";
            String requestBody = "{\"type\":\"READ\"}";
            Response response = performPatch(endpoint,Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test(dependsOnMethods = {
            "DealApp.Requests.CreateRequest.createRequestBySavedRea",
            "activateRequestsByNewRea"
    })

    public void Check_Hide_Requests()
        {
            String endpoint = "/request/" +requestIdSavedClient + "/interaction";
            String requestBody = "{\"type\":\"REJECT\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test(dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestBySavedRea")
    public void Check_Show_Requests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdBySavedRea + "/interaction";
            String requestBody = "{\"type\":\"REJECT\"}";

            Response response = performDelete(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }

    @Test(dependsOnMethods = {
            "DealApp.Requests.CreateRequest.createRequestsByNewRea",
            "activateRequestsByNewRea"
    })
    public void Check_Report_Requests() throws IOException
        {
            String endpoint = "/report";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/PropertyRequests/ReportRequest.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"request\": \"66bd9757209005078880d88d\"", "\"request\": \"" + requestIdByNewRea + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>(){});
            requestBody.put("request", String.valueOf(requestIdByNewRea));
            Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());;
            Assert.assertNotNull(response.jsonPath().getString("_id"),"report endpoint should return report id and id should not be null ");

        }

    @Test(dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea"})
    public void Check_Close_Requests()
        {
            String endpoint = "/request/"+CreateRequest.requestIdBySavedRea+"/status";
           Map<String,Object> requestBody=Map.of("status","CLOSED_NOT_INTERESTED");
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());

        }
    }
