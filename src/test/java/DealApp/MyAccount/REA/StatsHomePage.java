package DealApp.MyAccount.REA;

import org.testng.Assert;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;


public class StatsHomePage extends RestAssuredUtilities {
    @Test
    public void getStats(){
        String Endpoint= "/user/stats";
        Response response= performGet(Endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("interestRequests.total"));
        Assert.assertNotNull(response.jsonPath().getString("seriousRequests.total"));
        Assert.assertNotNull(response.jsonPath().getString("favoriteRequests.total"));
        Assert.assertNotNull(response.jsonPath().getString("ads.total"));
        Assert.assertNotNull(response.jsonPath().getString("marketingRequests.total"));
        Assert.assertNotNull(response.jsonPath().getString("projects.total"));

    }
}
