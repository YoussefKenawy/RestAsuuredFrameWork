package DealApp.MarketRequests;

import DealApp.ADS.CreateAd;
import DealApp.BaseTest;
import DealApp.MyAccount.REA.Rea;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static DealApp.ADS.CreateAd.adIDByNewRea;
import static DealApp.ADS.CreateAd.adId;
import static utilities.RestAssuredUtilities.sendHeaders;


public class CreateMarketingRequest extends BaseTest
    {
    public static String marketRequestId;

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAd"})
    public void addMarketRequest() throws IOException
        {
            String endpoint = "/marketing-requests";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/MarketingRequests/CreateMarketRequest.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"adId\": \"66b331b81c9102297a71b676\"", "\"adId\": \"" + adId + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            requestBody.put("adId", String.valueOf(adIDByNewRea));
            Response response = RestAssuredUtilities.performPost(endpoint, newReaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.getStatusCode(), 201);
            Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
            Assert.assertNotNull(response.jsonPath().getString("_id"), "not null ");
             marketRequestId = response.jsonPath().getString("_id");  // Store the ID
            Assert.assertEquals(response.jsonPath().getString("status"), "READY_FOR_REVIEW");

        }
    }
