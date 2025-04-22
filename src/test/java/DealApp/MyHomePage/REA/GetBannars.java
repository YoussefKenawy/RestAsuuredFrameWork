package DealApp.MyHomePage.REA;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetBannars extends RestAssuredUtilities {
    public static Map<String, Object> sendQueryParams() {
        Map<String, Object> queryParms = Map.of(

                "page", "1",
                "limit", "10"
                );
        return queryParms;
    }

    @Test
    public void getBannars() {
        String Endpoint = "/banner";
        Response response = performGet(Endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.jsonPath().getMap("data[0]").containsKey("_id"));
        Assert.assertTrue(response.jsonPath().getMap("data[0]").containsKey("title"));
        Assert.assertTrue(response.jsonPath().getMap("data[0]").containsKey("imageUrl"));
        Assert.assertTrue(response.jsonPath().getMap("data[0]").containsKey("linkTo"));
        Assert.assertTrue(response.jsonPath().getMap("data[0]").containsKey("description"));

    }
}
