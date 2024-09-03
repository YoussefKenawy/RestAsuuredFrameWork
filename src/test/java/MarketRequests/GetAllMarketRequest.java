/*
package MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetAllMarketRequest extends RestAssuredUtilities {

    @Test
    public void getAllMarketRequest()
    {
        String endpoint = "/marketing-requests";
        Response response = RestAssuredUtilities.performGet(endpoint, Tokens.getInstance().getAdminToken());
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}*/
