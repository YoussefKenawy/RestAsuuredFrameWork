package DealApp.Ratings.Rea;

import DealApp.BaseTest;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Tokens;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static utilities.RestAssuredUtilities.performPost;

import static utilities.RestAssuredUtilities.sendHeaders;

public class RateOtherRea extends BaseTest
    {

    public  static String reviewId;
    public int getRandomRatingValue()
        {
            Random random = new Random();
            return random.nextInt(5) + 1; // Generates a random integer from 1 to 5
        }

    @Test
    public void rateOtherRea()
        {
            String endpoint = "/user-reviews";
            Map<String, Object> requestBody = Map.of("reviewee", Rea.reaId, "note", "this is a note by API Automation script by youssef test", "rating", getRandomRatingValue(), "services", List.of("AD"));
            Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("note"), "this is a note by API Automation script by youssef test");
            Assert.assertTrue(response.jsonPath().getList("services").contains("AD"));
           reviewId=response.jsonPath().getString("_id");

        }

    }

