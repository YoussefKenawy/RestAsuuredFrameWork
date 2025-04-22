package DealApp.ADS;

import DealApp.BaseTest;
import DealApp.MyAccount.REA.Rea;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;
import java.io.IOException;
import java.util.Map;
import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.performPost;
import static utilities.RestAssuredUtilities.sendHeaders;

public class CreateAd extends BaseTest
{
    public static String adIDByNewRea;
public static String adId;

@Test
    public void createAd() throws IOException
    {

        String endpoint = "/ad";
        Map<String, Object> requestBody = getJsonDataAsMap("/Ads/CreateAd.json");
        Response response = performPost(endpoint,newReaToken, requestBody, sendHeaders());
        adIDByNewRea= response.jsonPath().getString("data._id");
        Assert.assertNotNull(adIDByNewRea, "ID should not be null");
        Assert.assertEquals(response.statusCode(), 201);
    }
@Test
public void createAdBySavedRea() throws IOException
    {

        String endpoint = "/ad";
        Map<String, Object> requestBody = getJsonDataAsMap("/Ads/CreateAd.json");
        Response response = performPost(endpoint,Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        String _id = response.jsonPath().getString("data._id");
        Assert.assertNotNull(_id, "ID should not be null");
        CreateAd.adId = _id;
        Assert.assertEquals(response.statusCode(), 201);
    }
}







