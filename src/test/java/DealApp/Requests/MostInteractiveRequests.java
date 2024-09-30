package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class MostInteractiveRequests extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParams( )
    {
        Map<String, Object> queryParams = Map.of(
                "sortBy", "interactionsCounts.total"
        );
        return queryParams;
    }


    @Test
    public void getSuitableAdsToGivenRequest()
    {
        String endpoint = "/request/interests";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(), 200);
    }


}
