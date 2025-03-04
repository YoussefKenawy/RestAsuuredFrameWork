package DealApp.DealOpportiunities;

import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static DealApp.DealOpportiunities.CreateNewProject.projectId;

public class ShowProjectInDealOpportunities extends RestAssuredUtilities
    {
    @Test(dependsOnMethods = {"DealApp.DealOpportiunities.CreateNewProject.createNewProject","DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany"})
    public void showProjectInDealOpportunities()
        {
            String endpoint = "/projects/" + projectId;
            Map<String,Object> requestBody=Map.of("isLaunched",true,"showOnMapForAgents",true);
            Response response=performPatch(endpoint,Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
            Assert.assertEquals(response.statusCode(),200);
        }
    }
