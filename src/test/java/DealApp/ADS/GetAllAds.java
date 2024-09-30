package DealApp.ADS;

import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAllAds extends RestAssuredUtilities {


    public static Map<String,Object> sendQueryParams()
    {
        Map<String, Object> queryParams = Map.of(
                "city","6009d941950ada00061eeeab"
        );
        return queryParams;
    }
    @Test
    public void getAllAds()
    {
      //  Map<String, Object> queryParams=sendQueryParams();
         String endpoint="/ad";
        performGet(endpoint,Tokens.getInstance().getClientToken(),sendQueryParams());
    }
}