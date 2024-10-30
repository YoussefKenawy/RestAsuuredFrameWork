package DealApp.Requests;
import DealApp.MyAccount.CLIENT.Client;
import DealApp.MyAccount.REA.Rea;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class InteractWithRequests extends RestAssuredUtilities
    {
    @Test (dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestsByNewRea"})
    public void activateRequestsByNewRea() throws InterruptedException
        {
            String endpoint = "/request/" + CreateRequest.requestIdByRea + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint,Rea.reaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }
    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient"})
    public void activateRequestsByNewClient() throws InterruptedException
        {
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint, Client.clientToken, requestBody, sendHeaders());
            // Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.cantRefreshBeforeTwoDays");
            Assert.assertEquals(response.statusCode(), 200);
        }
    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByClient",})
    public void activateRequestsBySavedClient() throws InterruptedException
        {
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint,Tokens.getInstance().getClientToken(),requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }
       @Test (dependsOnMethods ={"DealApp.Requests.CreateRequest.createRequestBySavedRea",})
    public void activateRequestsBySavedRea() throws InterruptedException
        {
            String endpoint = "/request/" + CreateRequest.requestIdByRea + "/activate";
            Map<String, Object> requestBody = Map.of("status", "ACTIVE");
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            // Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.cantRefreshBeforeTwoDays");
            Assert.assertEquals(response.statusCode(), 200);
        }


    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.MyAccount.REA.Rea.authorizeWithNafaz",
            "DealApp.Requests.CreateRequest.createRequestBySavedRea",
            "activateRequestsByNewRea",


    })
    public void Check_chat_Interaction_WithRequests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByRea + "/interaction";
            String requestBody = "{\"type\":\"CHAT\"}";
            Response response = performPatch(endpoint, Rea.reaToken, requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_WHATSAPP_Interaction_WithRequestsByRea() throws InterruptedException
        {
            Thread.sleep(2000);
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/interaction";
            String requestBody = "{\"type\":\"WHATSAPP\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_CALL_PHONE_Interaction_WithRequestsByRea() throws InterruptedException
        {
            Thread.sleep(10000);
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/interaction";
            String requestBody = "{\"type\":\"CALL_PHONE\"}";
            Response response = performPatch(endpoint,Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"})
    public void Check_FAVORITE_Interaction_WithRequestsByRea()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/interaction";
            String requestBody = "{\"type\":\"FAVORITE\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");
        }

    @Test(dependsOnMethods = {
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "activateRequestsByNewClient"

    })
    public void Check_READ_Interaction_WithRequests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/interaction";
            String requestBody = "{\"type\":\"READ\"}";
            Response response = performPatch(endpoint,Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.MyAccount.REA.Rea.authorizeWithNafaz",
            "DealApp.Requests.CreateRequest.createRequestBySavedRea",
            "activateRequestsByNewRea"
    })
    public void Check_ACCEPT_Interaction_WithRequests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByRea + "/interaction";
            String requestBody = "{\"type\":\"ACCEPT\"}";
            Response response = performPatch(endpoint, Rea.reaToken, requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test (dependsOnMethods ={
            "DealApp.Requests.CreateRequest.createRequestByClient",
            "activateRequestsBySavedClient"})
    public void Check_Hide_Requests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByClient + "/interaction";
            String requestBody = "{\"type\":\"REJECT\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("_id"), "should not be null ");

        }

    @Test(dependsOnMethods = "DealApp.Requests.CreateRequest.createRequestBySavedRea")
    public void Check_Show_Requests()
        {
            String endpoint = "/request/" + CreateRequest.requestIdByRea + "/interaction";
            String requestBody = "{\"type\":\"REJECT\"}";

            Response response = performDelete(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
        }

    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.MyAccount.REA.Rea.authorizeWithNafaz",
            "DealApp.Requests.CreateRequest.createRequestsByNewRea",
            "activateRequestsByNewRea"
    })
    public void Check_Report_Requests() throws IOException
        {
            String endpoint = "/report";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/PropertyRequests/ReportRequest.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"request\": \"66bd9757209005078880d88d\"", "\"request\": \"" + CreateRequest.requestIdByRea + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>(){});
            requestBody.put("request", String.valueOf(CreateRequest.requestIdByRea));
            Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());;
            Assert.assertNotNull(response.jsonPath().getString("_id"),"report endpoint should return report id and id should not be null ");

        }

    @Test(dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea"})
    public void Check_Close_Requests()
        {
            String endpoint = "/request/"+CreateRequest.requestIdByRea+"/status";
            String requestBody = "{\"status\":\"CLOSED_NOT_INTERESTED\"}";
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertNotNull(response.jsonPath().getString("error.code"), "error.request.notFound");

        }


    }
