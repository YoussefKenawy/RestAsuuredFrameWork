package DealApp.MyHomePage.REA;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;
public class ReaNearToYou extends RestAssuredUtilities {
    public static Map<String, Object> sendQueryParams() {
        Map<String, Object> queryParms = Map.of(

                "page", "1",
                "limit", "10",
                "lat","24.731759",
                "lng","46.692731"
        );
        return queryParms;
    }

    @Test
    public void getReaNearToYou() {
        String Endpoint = "/user/REA";
        Response response=performGet(Endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("data[0]._id"));
        Assert.assertEquals(response.jsonPath().getString("data[0].districtsOfInterest[0].city._id"),"6009d941950ada00061eeeab");
    }
}
