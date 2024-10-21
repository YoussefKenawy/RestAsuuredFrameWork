package DealApp.ADS;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;

public class GetAdsFromMap extends RestAssuredUtilities
{

    public static Map<String,Object> sendQueryParams()
    {
        Map<String,Object>queryParams=Map.of
                (
                        "page","1",
                        "paymentMethod","CASH",
                        "city","6009d941950ada00061eeeab",
                        "purpose","SALE",
                        "usageType","RESIDENTIAL",
                        "propertyAgeRange","NEW",
                        "sortBy","price",
                        "allDeveloperAds","false",
                        "isPromoted","false"
                );
        return  queryParams;
    }
    @Test
    public void getAdsFromMap()
    {
        String endpoint="/ad";
        Response response=performGet(endpoint, Tokens.getInstance().getGuestToken(),sendQueryParams());
    }
}
