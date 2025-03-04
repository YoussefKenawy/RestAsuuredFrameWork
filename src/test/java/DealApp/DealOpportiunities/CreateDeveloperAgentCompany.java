package DealApp.DealOpportiunities;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateDeveloperAgentCompany extends RestAssuredUtilities
    {
        public static String developerAgentId;
    @Test
    public void createDeveloperAgentCompany() throws IOException
        {

            String endpoint="/developer-agents";

            Map<String,Object> requestBody=getJsonDataAsMap("/DealOpportunites/createDeveloperAgent.json");
            Response response =performPost(endpoint, Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
            Assert.assertEquals(response.statusCode(),201);
             developerAgentId=response.jsonPath().getString("_id");
            Assert.assertNotNull(developerAgentId,"developer agent id should not be null");
        }
    }

