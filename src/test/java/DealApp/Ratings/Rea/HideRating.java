package DealApp.Ratings.Rea;

import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.List;
import java.util.Map;

public class HideRating extends RestAssuredUtilities
{
@Test(dependsOnMethods = {"DealApp.MyAccount.REA.Rea.reaRegister", "DealApp.MyAccount.REA.Rea.reaRequestOTP", "DealApp.MyAccount.REA.Rea.getOTP", "DealApp.MyAccount.REA.Rea.reaEnterOTP","DealApp.Ratings.Rea.RateOtherRea.rateOtherRea"})
public void hideRatingAddedByRea()
    {
        String endpoint = "/user-reviews/"+RateOtherRea.reviewId;
        Map<String, Object> requestBody = Map.of("status","HIDDEN");
        Response response = performPatch(endpoint, Rea.reaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "HIDDEN");
    }
@Test(dependsOnMethods = {"DealApp.MyAccount.REA.Rea.reaRegister", "DealApp.MyAccount.REA.Rea.reaRequestOTP", "DealApp.MyAccount.REA.Rea.getOTP", "DealApp.MyAccount.REA.Rea.reaEnterOTP","DealApp.Ratings.Client.CreateRating.createRating"})
public void hideRatingAddedByClient()
    {
        String endpoint = "/user-reviews/"+RateOtherRea.reviewId;
        Map<String, Object> requestBody = Map.of("status","HIDDEN");
        Response response = performPatch(endpoint, Rea.reaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "HIDDEN");
    }
}
