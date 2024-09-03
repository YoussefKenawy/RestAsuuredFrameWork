/*
package MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetMarketRequestById extends RestAssuredUtilities {
    @Test
    public void getMarketRequests()
    {
        String endpoint = "/marketing-requests/659c065a960cec097004a5bb";
        Response response = RestAssuredUtilities.performGet(endpoint, Tokens.getInstance().getAdminToken());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
*/
