package DealApp.Ratings.Client;

import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CreateRating extends RestAssuredUtilities
    {
    public int getRandomRatingValue()
        {
            Random random = new Random();
            return random.nextInt(5) + 1; // Generates a random integer from 1 to 5
        }


    @Test(dependsOnMethods = {"DealApp.MyAccount.REA.Rea.reaRegister", "DealApp.MyAccount.REA.Rea.reaRequestOTP", "DealApp.MyAccount.REA.Rea.getOTP", "DealApp.MyAccount.REA.Rea.reaEnterOTP"})
    public void createRating()
        {
            String endpoint = "/user-reviews";
            Map<String, Object> requestBody = Map.of("reviewee", Rea.reaId, "note", "this is a note by API  Automation script by youssef test", "rating", getRandomRatingValue(), "services", List.of("AD"));
            Response response = performPost(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertTrue(response.jsonPath().getList("services").contains("AD"));
        }
    }
