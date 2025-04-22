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
import java.util.List;
import java.util.Map;

import static DealApp.DealOpportiunities.AddUnitIntoProject.unitId;
import static DealApp.DealOpportiunities.CreateDeveloperAgentCompany.developerAgentId;
import static DealApp.DealOpportiunities.CreateNewProject.projectId;

public class DealOpportunitiesFeature extends RestAssuredUtilities
    {
    public static String clientId;

    @Test(priority = 1, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject",})
    public void StartMarketingProjectByAcceptTerms() throws IOException
        {
            String endpoint = "/projects/" + projectId + "/add-to-terms"; // Added missing slash here
            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken());

            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("isLaunched"), "true");
        }

    @Test(priority = 2, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject"})
            public void getDealOpportunities()
        {
            String endpoint = "/projects";
            Map<String,Object>queryParams=Map.of("page","1","limit","10","sort","-1","sortBy","createdAt");
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken(),queryParams);

            Assert.assertEquals(response.statusCode(), 200);

            // Check that 'data' array is not empty before making assertions on its elements
           // Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "Data list is empty.");
            // Validate specific fields within the first data object if 'data' is not empty
           // Assert.assertEquals(response.jsonPath().getString("data[0]._id"), projectId,"Project id should be correct ");
           // Assert.assertEquals(response.jsonPath().getString("data[0].name"), "AutomationProjecTByYoussef");
          //  Assert.assertEquals(response.jsonPath().getString("data[0].developerAgent._id"), developerAgentId,"company Id should be correct");
        }
    @Test(priority = 7, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject", "addClientToDealOpportunityProject","StartMarketingProjectByAcceptTerms"})
    public void getDeveloperAgentCompaniesStats()
        {
            String endpoint = "/user/stats";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("projects.total"), "1");
            Assert.assertNotNull(response.jsonPath().getString("projects.subscribed"), "1");
            Assert.assertNotNull(response.jsonPath().getString("projects.percentage"), "100%");

        }

    @Test(priority = 3, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject"})
    public void openDealOpportunityProject()
        {
            String endpoint = "/projects/" + projectId;
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("_id"), projectId);
            Assert.assertEquals(response.jsonPath().getString("name"), "AutomationProjecTByYoussef");
            Assert.assertEquals(response.jsonPath().getString("developerAgent._id"), developerAgentId);
        }


    @Test(priority = 4, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject","StartMarketingProjectByAcceptTerms"})
    public void addClientToDealOpportunityProject() throws IOException
        {
            String endpoint = "/customer-leads/project";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/DealOpportunites/addClientToDealOpportunityProject.json")), StandardCharsets.UTF_8);
            requestBodyJson = requestBodyJson.replace("\"project\": \"123\"", "\"project\": \"" + projectId + "\"")
                    .replace("\"interestedUnits\": \"123\"", "\"interestedUnits\": \"" + unitId + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<>()
                {
                });
            requestBody.put("project", projectId);
            requestBody.put("interestedUnits", unitId);
            Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("name"), "YoussefClientByAutomation");
            Assert.assertEquals(response.jsonPath().getString("phone"), "966141197777");
            Assert.assertEquals(response.jsonPath().getString("project"), projectId);
            Assert.assertEquals(response.jsonPath().getString("developerAgent"), developerAgentId);
             clientId = response.jsonPath().getString("_id");
            Assert.assertNotNull(clientId);
            Assert.assertNotNull(response.jsonPath().getString("createdAt"));
        }

    @Test(priority = 5, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject", "addClientToDealOpportunityProject","StartMarketingProjectByAcceptTerms"})
    public void getClientsAddedToDealOpportunityProject()
        {            Map<String, Object> sendQueryParams = Map.of("page", "1", "limit", "10", "project", projectId);

            String endpoint = "/customer-leads/agents";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams);
            Assert.assertEquals(response.statusCode(), 200);
            List<String> idList = response.jsonPath().getList("data._id", String.class);
            String firstId = idList.get(0);
            Assert.assertEquals(firstId, clientId, "The first _id in data does not match the expected clientId.");
            Boolean isCustomerInformedAboutUnit = response.jsonPath().getBoolean("data[0].isCustomerInformedAboutUnit");
            Boolean isCustomerWilingToBuy = response.jsonPath().getBoolean("data[0].isCustomerWilingToBuy"); // Corrected field name
            Assert.assertTrue(isCustomerInformedAboutUnit);
            Assert.assertTrue(isCustomerWilingToBuy);
        }

    @Test(priority = 6, dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject", "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany", "DealApp.DealOpportiunities.ShowProjectInDealOpportunities.showProjectInDealOpportunities", "DealApp.DealOpportiunities.AddUnitIntoProject.addUnitIntoProject", "addClientToDealOpportunityProject","StartMarketingProjectByAcceptTerms"})
    public void filterClientsAddedToDealOpportunityProject()
        {
            Map<String, Object> sendQueryParams = Map.of("page", "1", "limit", "10", "status", "NOT_CONTACTED,NO_ANSWER,NOT_INTERESTED,SERIOUS_CUSTOMER,DEAL_DONE", "project", projectId, "addedBy", "AGENT");
            String endpoint = "/customer-leads/agents";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams);
            Assert.assertEquals(response.statusCode(), 200);
           Assert.assertEquals(response.jsonPath().getString("data[0]._id"), clientId);
            Assert.assertEquals(response.jsonPath().getString("data[0].developerAgent"), developerAgentId);
            Assert.assertEquals(response.jsonPath().getString("data[0].project._id"), projectId);
            Boolean isCustomerInformedAboutUnit = response.jsonPath().getBoolean("data[0].isCustomerInformedAboutUnit");
            Boolean isCustomerWilingToBuy = response.jsonPath().getBoolean("data[0].isCustomerWilingToBuy"); // Corrected field name
            Assert.assertTrue(isCustomerInformedAboutUnit);
            Assert.assertTrue(isCustomerWilingToBuy);
        }
    }
