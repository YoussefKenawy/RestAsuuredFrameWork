package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetMyRequests extends RestAssuredUtilities {

    public static Map<String, Object> sendQueryParams()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "10"
                );

        return queryParams;

    }

    @Test
    public void getMyRequestsAsRea()
    {
        String endpoint = "/request/mine";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }
    @Test
    public void getMyRequestsAsClient()
    {
        String endpoint = "/request/mine";
        Response response = performGet(endpoint, Tokens.getInstance().getClientToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");

    }
}


