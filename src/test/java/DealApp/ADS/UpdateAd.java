package DealApp.ADS;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

import java.io.IOException;
import java.util.Map;

public class UpdateAd extends RestAssuredUtilities {
    @Test(dependsOnMethods = "DealApp.ADS.CreateAd.createAd")
    public void updateAd() throws IOException
    {
        String endpoint = "/ad/"+CreateAd.adId;
        Map<String, Object> requestBody = getJsonDataAsMap("/Ads/UpdateAd.json");
        Response response = performPut(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data.updatedAt"), "should npt be null ");
    }
}
