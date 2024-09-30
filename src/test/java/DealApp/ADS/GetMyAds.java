package DealApp.ADS;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetMyAds extends RestAssuredUtilities {
    public static Map<String, Object> sendQueryParams()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page","1",
                        "limit","10",
                        "published","false"
                );

        return queryParams;

    }

    @Test
    public void getMyAds()
    {
        String endpoint="/ad/mine";
        Response response= performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
    }
}
