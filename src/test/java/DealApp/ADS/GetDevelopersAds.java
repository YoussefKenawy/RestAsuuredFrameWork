package DealApp.ADS;

import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetDevelopersAds extends RestAssuredUtilities {

    public static Map<String,Object> sendQueryParams()
    {
        Map<String, Object> queryParams = Map.of(
                "city","6009d941950ada00061eeeab"
        );
        return queryParams;
    }
    @Test
    public void getAllDevelopersAdsInAGivenCity()
    {
        Map<String, Object> queryParams=sendQueryParams();
        String endpoint="/ad/developers-ads";
        performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams());
    }
}
