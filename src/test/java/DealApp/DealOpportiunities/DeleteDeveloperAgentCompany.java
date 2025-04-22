package DealApp.DealOpportiunities;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static DealApp.DealOpportiunities.CreateDeveloperAgentCompany.developerAgentId;
import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class DeleteDeveloperAgentCompany extends RestAssuredUtilities
    {
    @Test(dependsOnMethods = "DealApp.DealOpportiunities.CreateDeveloperAgentCompany.createDeveloperAgentCompany")
    public void deleteDeveloperAgentCompany()
        {
            String endpoint="/developer-agents/"+developerAgentId;
            Response response =performDelete(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.statusCode(),200);

        }

    }
