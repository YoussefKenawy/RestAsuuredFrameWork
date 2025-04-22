package LoyaltySystem.PerofrmActions;
import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Tokens;
import java.io.IOException;
import static utilities.RestAssuredUtilities.performGet;
public class DocumentDealDoneInsideDeal extends BaseTest
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
            System.out.println("*************************************** points Before Perform Action :" +pointsBeforePerformAction);
        }
    @Test(priority = 2)
    public void documentDealInsideDeal() throws IOException, InterruptedException
        {
            DealApp.MyDeals.PostMyDeal.postMyDeal();
            DealApp.MyDeals.PostMyDeal.adminApproveMyDeal();
            Thread.sleep(5000);

        }
    @Test(priority = 3, dependsOnMethods = {"documentDealInsideDeal"})
    public void getProfilePointsAfterAction() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            pointsAfterPerformAction = response.jsonPath().getInt("data.loyalty.earned");
            System.out.println("*************************************** points After Perform Action :" +pointsAfterPerformAction);
            Thread.sleep(5000);

        }
    @Test(priority = 4,dependsOnMethods ={ "getProfilePointsBeforeAction","documentDealInsideDeal","getProfilePointsAfterAction"})
    public void checkDocumentDealInsideDealPoints() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,Tokens.getInstance().getReaToken());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(pointsAfterPerformAction, pointsBeforePerformAction + 10,"Earned points should increase by 10 after Action ");
        }
    }
