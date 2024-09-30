package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetInterstedRequests extends RestAssuredUtilities {
    public static Map<String, Object> sendQueryParamsToGetNewRequests( )
    {
        Map<String, Object> queryParams = Map.of
                (
                        "limit", "10",
                        "filter", "NEW",
                        "mixRequests", true,
                        "normalPage", 1,
                        "seriousPage", 1

                );
        return queryParams;
    }

    @Test
    public void Check_InterestedRequests_NewRequest()
    {
        String endpoint = "/request/interests";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsToGetNewRequests());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }
    public static Map<String, Object> sendQueryParamsToGeSeriousInterestedInRequests( )
    {
        Map<String, Object> queryParams = Map.of
                (
                        "limit", "10",
                        "requestType", "SERIOUS"

                );
        return queryParams;
    }
    @Test
    public void Check_InterestedRequests_SeriousRequest()
    {
        String endpoint = "/request/interests";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsToGeSeriousInterestedInRequests());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should not be null ");
    }
    public static Map<String, Object> sendQueryParamsToGetFavoriteInterestedInRequests( )
    {
        Map<String, Object> queryParams = Map.of
                (
                        "limit", "10",
                        "interactionsType", "FAVORITE",
                        "sortBy", "favoriteBy.at"

                );
        return queryParams;
    }
    @Test
    public void Check_InterestedRequests_FavoriteRequest()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsToGetFavoriteInterestedInRequests());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }
   public static Map<String, Object> sendQueryParamsToGetAllInterestedInRequests( )
    {
        Map<String, Object> queryParams = Map.of
                (
                        "limit", "10",
                        "mixRequests", true,
                        "normalPage", 1,
                        "seriousPage",1

                );
        return queryParams;
    }

 @Test
    public void Check_InterestedRequests_AllRequests()
    {
        String endpoint = "/request/interests";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsToGetAllInterestedInRequests());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

}
