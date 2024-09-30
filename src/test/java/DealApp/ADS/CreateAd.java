package DealApp.ADS;

import DealApp.MarketRequests.CreateMarketingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;
import java.io.IOException;
import java.util.Map;
import static utilities.JsonUtilitiles.getJsonDataAsMap;
public class CreateAd extends RestAssuredUtilities
{
    public static String adId;
    @Test
    public void createAd() throws IOException
    {

        String endpoint = "/ad";
        Map<String, Object> requestBody = getJsonDataAsMap("/Ads/CreateAd.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        String _id = response.jsonPath().getString("data._id");
        Assert.assertNotNull(_id, "ID should not be null");
        CreateAd.adId = _id;
        Assert.assertEquals(response.statusCode(), 201);

    }
}



