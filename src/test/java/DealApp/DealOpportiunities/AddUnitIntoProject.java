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
import static DealApp.DealOpportiunities.CreateNewProject.projectId;
public class AddUnitIntoProject extends RestAssuredUtilities
    {
        public static String unitId;
    @Test(dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject","DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany"})

    public void addUnitIntoProject() throws IOException
        {
            String endpoint="/project-units";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/DealOpportunites/addUnitIntoProject.json")), StandardCharsets.UTF_8);
            requestBodyJson = requestBodyJson.replace("\"projectId\": \"123\"", "\"projectId\": \"" + projectId + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>() {});
            requestBody.put("projectId", String.valueOf(projectId));

            Response response =performPost(endpoint, Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
            Assert.assertEquals(response.statusCode(),201);
             unitId=response.jsonPath().getString("_id");
            Assert.assertNotNull(projectId,"Project   id should not be null");
            Assert.assertEquals(response.jsonPath().getString("project"),projectId);

            Assert.assertEquals(response.jsonPath().getString("name"),"Automation Units By Youssef");
        }
    }


