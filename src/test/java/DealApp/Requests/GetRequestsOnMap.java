package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetRequestsOnMap extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParams( )
    {
        Map<String, Object> queryParams = Map.of(
                "cities", " 6009d941950ada00061eeeab",
                "limit",30
        );
        return queryParams;
    }

    @Test
    public void getRequestsFromMap()
    {
        String endpoint = "/district/map-details";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
