package LoyaltySystem.RedeemProductsWithLoyaltyPoints;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static utilities.RestAssuredUtilities.*;
import static utilities.RestAssuredUtilities.performGet;

public class SubscribePremiumOneYearPackageUsingPoints extends BaseTest
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
    public void submitInterestPoint()
        {
            String endpoint = "/user/profile";
            Map<String, Object> requestBody = new HashMap<>();
            // City and Districts
            Map<String, Object> cityDistrict = new HashMap<>();
            cityDistrict.put("cityId", "5dd49004af8af4002cbfae6e");
            cityDistrict.put("districtsId", Collections.singletonList("5dd490b3bc2e91004242ec0b"));

            // Properties of Interest
            Map<String, Object> propertyInterest = new HashMap<>();
            propertyInterest.put("specialty", Collections.singletonList("SALE"));
            propertyInterest.put("propertyType", "6309182645e4ba692a2d5cc0");

            // Adding lists to the main body
            requestBody.put("cityDistrictOfInterest", Collections.singletonList(cityDistrict));
            requestBody.put("propertiesOfInterest", Collections.singletonList(propertyInterest));
            Response response = performPut(endpoint, newReaToken, requestBody, sendHeaders());

        }
    @Test(priority = 3, dependsOnMethods = {"submitInterestPoint"})
    public void getProfilePointsAfterAction() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,newReaToken);
            Assert.assertEquals(response.statusCode(), 200);
            pointsAfterPerformAction = response.jsonPath().getInt("data.loyalty.earned");
            System.out.println("*************************************** points After Perform Action :" +pointsAfterPerformAction);
            Thread.sleep(5000);

        }
    @Test(priority = 4,dependsOnMethods ={ "getProfilePointsBeforeAction","submitInterestPoint","getProfilePointsAfterAction"})

    public void checkSubmitInterestsPoints() throws InterruptedException
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint,newReaToken);
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(pointsAfterPerformAction, pointsBeforePerformAction + 10,"Earned points should increase by 10 after Action ");
        }
    @Test(priority = 5,dependsOnMethods ={ "getProfilePointsBeforeAction","submitInterestPoint","getProfilePointsAfterAction","checkSubmitInterestsPoints"})
    public void redeemPointForSubscribePremiumOneMonthPackage() throws InterruptedException
        {
            Thread.sleep(5000);
            Map<String, Object> requestBody = Map.of("packageName", "PREMIUM_ONE_YEAR", "paymentMethod", "LOYALTY_CREDIT");
            String endpoint = "/subscribe-package";
            Response response = performPatch(endpoint, newReaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("data.subscription.packageName"), "PREMIUM_ONE_YEAR");

        }

    }
