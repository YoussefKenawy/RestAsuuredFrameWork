package LoyaltySystem.PerofrmActions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;


public class AddRequestToGetPoints extends RestAssuredUtilities
    {
    public int pointsBeforePerformAction;
    public int pointsAfterPerformAction;

    @Test(priority = 1)
    public void getProfilePointsBeforeAction() throws InterruptedException
        {
            LoyaltySystem.PrepareLoyaltySettings.setLoyltyActions.setActionPoints();
            String endpoint = "/user/profile";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            pointsBeforePerformAction = response.jsonPath().getInt("data.loyalty.earned");
            Thread.sleep(5000);

        }

    @Test(priority = 2 ,dependsOnMethods = {"DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea"})
    public void performAction() throws IOException, InterruptedException
        {
            Thread.sleep(5000);
        }

    @Test(priority = 3, dependsOnMethods = {"performAction","DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea"})
    public void getProfilePointsAfterAction() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            pointsAfterPerformAction = response.jsonPath().getInt("data.loyalty.earned");
        }

    @Test(priority = 4, dependsOnMethods = {"getProfilePointsBeforeAction","DealApp.Requests.CreateRequest.createRequestBySavedRea","DealApp.Requests.InteractWithRequests.activateRequestsBySavedRea", "performAction", "getProfilePointsAfterAction"})

    public void checkAddingRequestPoints() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(pointsAfterPerformAction, pointsBeforePerformAction + 10, "Earned points should increase by 10 after Action ");
        }
    }