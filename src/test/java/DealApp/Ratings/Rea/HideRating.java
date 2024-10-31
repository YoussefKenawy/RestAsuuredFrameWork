package DealApp.Ratings.Rea;
import DealApp.BaseTest;
import DealApp.Ratings.Client.CreateRating;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static utilities.RestAssuredUtilities.performPatch;
import static utilities.RestAssuredUtilities.sendHeaders;

public class HideRating extends BaseTest
{
@Test(dependsOnMethods = {"DealApp.Ratings.Rea.RateOtherRea.rateOtherRea"})
public void hideRatingAddedByRea()
    {
        String endpoint = "/user-reviews/"+RateOtherRea.reviewId;
        Map<String, Object> requestBody = Map.of("status","HIDDEN");
        Response response = performPatch(endpoint,newReaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "HIDDEN");
    }
@Test(dependsOnMethods = {"DealApp.Ratings.Client.CreateRating.createRating"})
public void hideRatingAddedByClient()
    {
        String endpoint = "/user-reviews/"+ CreateRating.reviewId;
        Map<String, Object> requestBody = Map.of("status","HIDDEN");
        Response response = performPatch(endpoint,newReaToken, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "HIDDEN");
    }
}
