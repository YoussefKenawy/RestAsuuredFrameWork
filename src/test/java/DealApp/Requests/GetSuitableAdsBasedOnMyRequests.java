package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetSuitableAdsBasedOnMyRequests extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParams( )
    {
        Map<String, Object> queryParams = Map.of(
                "requestId", "66b32f352891d185621f1d9c",
                "nearestDistricts",true,
                "allDeveloperAds",true
        );
        return queryParams;
    }

    @Test
    public void getSuitableAdsToGivenRequest()
    {
        String endpoint = "/ad";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
