package LoyaltySystem.PerofrmActions;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;

import static utilities.RestAssuredUtilities.performGet;

public class communicateWithRequestToGetPoints extends BaseTest
    {
        public int pointsBeforePerformAction;
        public int pointsAfterPerformAction;
        @Test(priority = 1)
        public void getProfilePointsBeforeAction() throws InterruptedException
        {
            LoyaltySystem.PrepareLoyaltySettings.setLoyltyActions.setActionPoints();
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,newReaToken);
            Assert.assertEquals(response.statusCode(), 200);
            pointsBeforePerformAction = response.jsonPath().getInt("data.loyalty.earned");
            System.out.println("*************************************** points Before Perform Action :" +pointsBeforePerformAction);
            Thread.sleep(5000);

        }

        @Test(priority = 2)
        public void performAction() throws IOException, InterruptedException
        {
            DealApp.Requests.CreateRequest.createRequestBySavedRea();
            DealApp.Requests.InteractWithRequests.Check_chat_Interaction_WithRequests();
            Thread.sleep(5000);
        }
        @Test(priority = 3, dependsOnMethods = {"performAction"})
        public void getProfilePointsAfterAction() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,newReaToken);
            Assert.assertEquals(response.statusCode(), 200);
            pointsAfterPerformAction = response.jsonPath().getInt("data.loyalty.earned");
            System.out.println("*************************************** points After Perform Action :" +pointsAfterPerformAction);
            Thread.sleep(5000);

        }

        @Test(priority = 4,dependsOnMethods ={ "getProfilePointsBeforeAction","performAction","getProfilePointsAfterAction"})

        public void checkCommunicationWithRequestPoints() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,newReaToken);
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(pointsAfterPerformAction, pointsBeforePerformAction + 10,"Earned points should increase by 10 after Action ");
        }
    }
