package DealApp.MyDeals;

import DealApp.ADS.CreateAd;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class PostMyDeal extends RestAssuredUtilities {
    public static String myDealsId;
    @Test
    public void postMyDeal() throws IOException {
        String endpoint ="/closed-deals";
        Map<String, Object> requestBody = getJsonDataAsMap("/MyDeals/CreateMyDeals.json");
        Response response= performPost(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
       String _id = response.jsonPath().getString("data._id");
        Assert.assertNotNull(_id, "ID should not be null");
       PostMyDeal.myDealsId = _id;
       Assert.assertEquals(response.statusCode(), 201);








    }
}
