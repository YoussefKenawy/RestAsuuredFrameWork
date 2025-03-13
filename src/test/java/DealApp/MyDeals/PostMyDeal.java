package DealApp.MyDeals;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.*;

public class PostMyDeal extends BaseTest
    {
    public static String myDealsId;

    @Test
    public static void postMyDeal() throws IOException {
        String endpoint = "/closed-deals";
        Map<String, Object> requestBody = getJsonDataAsMap("/MyAccount/MyDeals/CreateMyDeals.json");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        String _id = response.jsonPath().getString("data._id");
        Assert.assertNotNull(_id, "ID should not be null");
        PostMyDeal.myDealsId = _id;
        Assert.assertEquals(response.statusCode(), 201);
    }
    @Test(dependsOnMethods = "postMyDeal")
    public static void adminApproveMyDeal()
        {
    String endpoint = "/closed-deals/"+myDealsId+"/status";
    Map<String, Object> requestBody = Map.of("status","APPROVED");
    Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
