package DealApp.MarketRequests;

import DealApp.ADS.CreateAd;
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

import static DealApp.MarketRequests.CreateMarketingRequest.marketRequestId;

public class CreateMarketingProposal extends RestAssuredUtilities
    {
    public static String mkProposalID;
    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.ADS.CreateAd.createAd",
            "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest",
            "DealApp.MarketRequests.ChangeStatusByAdmin.changeStatusByAdmin_ToApprove"
    })
    public void createMarketingProposal() throws IOException
        {
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/MarketingRequests/CreateMarketingProposal.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"marketingRequest\": \"66788ef2f9a62f0f9a5b29c4\"", "\"marketingRequest\": \"" + marketRequestId + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>() {});
            String endpoint = "/marketing-proposals";
            requestBody.put("marketingRequest", String.valueOf(marketRequestId));
            Response response = RestAssuredUtilities.performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.getStatusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("status"), "PENDING");
            mkProposalID = response.jsonPath().getString("_id");

        }
    }
