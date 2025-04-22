package DealApp.ADS;

import io.restassured.internal.mapping.JsonbMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;
import java.io.IOException;
import java.util.Map;
import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class InteractionsWithAds extends RestAssuredUtilities {


    @Test (dependsOnMethods = "DealApp.ADS.CreateAd.createAdBySavedRea")
    public void putAdAsFavorite() throws IOException
    {
        String endpoint = "/ad/"+ CreateAd.adId+"/interaction";
        Map<String, Object> requestBody = getJsonDataAsMap("/ADS/PutAdAsFavorite.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("_id"));
    }


    @Test (dependsOnMethods = "DealApp.ADS.CreateAd.createAdBySavedRea")
    public void interactWithAdByWhatsapp() throws IOException
    {
        String endpoint = "/ad/"+ CreateAd.adId+"/interaction";
        Map<String, Object> requestBody = getJsonDataAsMap("/ADS/InteractWithAdByWhatsapp.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
    }

}