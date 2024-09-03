package DealApp.ADS;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetAnAd extends RestAssuredUtilities {

    @Test
    public void getAdById()
    {
        String endpoint = "/ad/66d5b164248081469ec748a8";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("data.id"), "66d5b164248081469ec748a8");

    }

}

