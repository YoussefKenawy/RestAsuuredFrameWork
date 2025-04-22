package DealApp.DealOpportiunities;

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

import static DealApp.DealOpportiunities.CreateDeveloperAgentCompany.developerAgentId;

public class CreateNewProject extends RestAssuredUtilities
    {
        public static String projectId;
    @Test(dependsOnMethods = "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany")
    public void createNewProject() throws IOException
        {
            String endpoint="/projects";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/DealOpportunites/createProject.json")), StandardCharsets.UTF_8);
            requestBodyJson = requestBodyJson.replace("\"developerAgent\": \"123\"", "\"developerAgent\": \"" + developerAgentId + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>() {});
            requestBody.put("developerAgent", String.valueOf(developerAgentId));
            Response response =performPost(endpoint, Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
            Assert.assertEquals(response.statusCode(),201);
             projectId=response.jsonPath().getString("_id");
            Assert.assertNotNull(projectId,"Project   id should not be null");
        }
    }
