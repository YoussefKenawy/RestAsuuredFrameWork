package DealApp.MyDeals;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetMyDeals extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParams()
    {
        Map<String, Object> queryParms = Map.of(

                "page", "1",
                "limit", "10"
        );
        return queryParms;
    }

    @Test(dependsOnMethods = "DealApp.MyDeals.PostMyDeal.postMyDeal")
    public void getMyDeals() {
        String endpoint = "/closed-deals/mine";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data[0]._id"), PostMyDeal.myDealsId);
        Assert.assertEquals(response.jsonPath().getString("data[0].status"), "PENDING");
    }
}
