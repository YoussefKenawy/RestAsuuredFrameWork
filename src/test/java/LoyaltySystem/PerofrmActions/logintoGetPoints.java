package LoyaltySystem.PerofrmActions;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static DealApp.MyAccount.REA.Rea.reaToken;
import static utilities.RestAssuredUtilities.performGet;

public class logintoGetPoints extends BaseTest
    {
    public int pointsAfterPerformAction;
    @Test(priority = 1)
    public void login() throws IOException, InterruptedException
        {
            DealApp.MyAccount.REA.Rea.reaRegister();
            DealApp.MyAccount.REA.Rea.reaRequestOTP();
            DealApp.MyAccount.REA.Rea.getOTP();
            DealApp.MyAccount.REA.Rea.reaEnterOTP();
            Thread.sleep(5000);
        }
    @Test(priority = 2, dependsOnMethods = {"login"})
    public void getProfilePointsAfterAction() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,reaToken);
            Assert.assertEquals(response.statusCode(), 200);
            pointsAfterPerformAction = response.jsonPath().getInt("data.loyalty.earned");
            System.out.println("*************************************** points After Perform Action :" +pointsAfterPerformAction);
            Thread.sleep(5000);
        }

    @Test(priority = 3,dependsOnMethods ={ "login","getProfilePointsAfterAction"})

    public void checkDailyLoginPoints() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,reaToken);
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(pointsAfterPerformAction,   0,"Earned points should increase by 10 after Action ");
        }
    }





