package DealApp.Ratings.Client;

import DealApp.BaseTest;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.List;
import java.util.Map;

import static utilities.RestAssuredUtilities.performGet;

public class ListReaRatings extends BaseTest
    {


    @Test(dependsOnMethods = {"DealApp.Ratings.Client.CreateRating.createRating"})
    public void listReaRatingsDefaultOrder()
        {
            Map<String, Object> sendQueryParam = Map.of("reviewee", Rea.reaId);
            String endpoint = "/user-reviews";
            Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParam);
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("data[0].reviewer._id"), "64884aafeab003eb526fbdca");
        }

    @Test(dependsOnMethods = {"DealApp.Ratings.Client.CreateRating.createRating"})
    public void listReaRatingsFromHighestToLowest()
        {
            Map<String, Object> sendQueryParam = Map.of("reviewee", Rea.reaId, "sortBy", "rating", "sort", "-1");
            String endpoint = "/user-reviews";
            Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParam);

            List<Map<String, Object>> reviews = response.jsonPath().getList("data");
            if (reviews.size() == 1)
                {
                    Assert.assertEquals(reviews.size(), 1, "There should be exactly one review.");
                } else
                {
                    for (int i = 0; i < reviews.size() - 1; i++)
                        {
                            int currentRating = (int) reviews.get(i).get("rating");
                            int nextRating = (int) reviews.get(i + 1).get("rating");
                            Assert.assertTrue(currentRating >= nextRating, "Reviews are not sorted from highest to lowest rating");
                        }
                }
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("data[0].reviewer._id"), "64884aafeab003eb526fbdca");
        }

    @Test(dependsOnMethods =  "DealApp.Ratings.Client.CreateRating.createRating")
    public void listReaRatingsFromLowestToHighest()
        {
            Map<String, Object> sendQueryParam = Map.of("reviewee", Rea.reaId, "sortBy", "rating", "sort", "1");

            String endpoint = "/user-reviews";
            Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParam);
            List<Map<String, Object>> reviews = response.jsonPath().getList("data");
            if (reviews.size() == 1)
                {
                    Assert.assertEquals(reviews.size(), 1, "There should be exactly one review.");
                } else
                {
                    for (int i = 0; i < reviews.size() - 1; i++)
                        {
                            int currentRating = (int) reviews.get(i).get("rating");
                            int nextRating = (int) reviews.get(i + 1).get("rating");
                            Assert.assertTrue(currentRating <= nextRating, "Reviews are not sorted from highest to lowest rating");
                        }
                }
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("data[0].reviewer._id"), "64884aafeab003eb526fbdca");
        }

    @Test(dependsOnMethods =  "DealApp.Ratings.Client.CreateRating.createRating")
    public void listReaRatingsFromNewestToOldest()
        {
            Map<String, Object> sendQueryParam = Map.of("reviewee", Rea.reaId, "sortBy", "createdAt", "sort", "-1");

            String endpoint = "/user-reviews";
            Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParam);
            List<Map<String, Object>> reviews = response.jsonPath().getList("data");
            if (reviews.size() == 1)
                {
                    Assert.assertEquals(reviews.size(), 1, "There should be exactly one review.");
                } else
                {
                    for (int i = 0; i < reviews.size() - 1; i++)
                        {
                            String currentCreatedAt = (String) reviews.get(i).get("createdAt");
                            String nextCreatedAt = (String) reviews.get(i + 1).get("createdAt");
                            Assert.assertTrue(currentCreatedAt.compareTo(nextCreatedAt) >= 0, "Reviews are not sorted from newest to oldest");
                        }
                }
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("data[0].reviewer._id"), "64884aafeab003eb526fbdca");
        }

    @Test(dependsOnMethods =  "DealApp.Ratings.Client.CreateRating.createRating")
    public void listReaRatingsFromOldestToNewest()
        {
            Map<String, Object> sendQueryParam = Map.of("reviewee", Rea.reaId, "sortBy", "createdAt", "sort", "1");
            String endpoint = "/user-reviews";
            Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParam);
            List<Map<String, Object>> reviews = response.jsonPath().getList("data");

            if (reviews.size() == 1)
                {
                    Assert.assertEquals(reviews.size(), 1, "There should be exactly one review.");
                } else
                {
                    for (int i = 0; i < reviews.size() - 1; i++)
                        {
                            String currentCreatedAt = (String) reviews.get(i).get("createdAt");
                            String nextCreatedAt = (String) reviews.get(i + 1).get("createdAt");
                            Assert.assertTrue(currentCreatedAt.compareTo(nextCreatedAt) <= 0, "Reviews are not sorted from newest to oldest");
                        }
                }
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().get("data[0].reviewer._id"), "64884aafeab003eb526fbdca");
        }


    }
