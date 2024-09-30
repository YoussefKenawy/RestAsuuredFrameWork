package DealApp.ADS;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetMostInteractiveAds extends RestAssuredUtilities {

    public static Map<String,Object> sendQueryParams()
    {
        Map<String,Object>queryParams=Map.of
                (
                        "page","1",
                        "limit","10",
                        "sortBy","interactionsCounts.total",
                        "lat","24.731759",
                        "lng","46.692731"
                );
        return  queryParams;
    }

    @Test
    public void getMostInteractiveAdsByRea()
    {
        String endpoint="/ad";
        Map<String, Object> queryParams=sendQueryParams();
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
    }
    @Test
    public void getMostInteractiveAdsByGuest()
    {
        String endpoint="/ad";
        Map<String, Object> queryParams=sendQueryParams();
        Response response=performGet(endpoint, Tokens.getInstance().getGuestToken(),sendQueryParams());
    }
}
