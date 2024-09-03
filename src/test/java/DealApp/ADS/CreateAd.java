package DealApp.ADS;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateAd extends RestAssuredUtilities {


    @Test
    public void createAd() throws IOException
    {

        String endpoint = "/ad";
        Map<String, Object> requestBody = getJsonDataAsMap("/Ads/CreateAd.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        String id = response.jsonPath().getString("data._id");
        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertEquals(response.statusCode(),201);
    }


}
