package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class GetInterestedDevelopers extends RestAssuredUtilities {

    public static Map<String, Object> sendQueryParams( )
    {
        Map<String, Object> queryParams = Map.of(
                "requestId", "66d98abf3b89f147828bb5b7",
                "timeline",true
        );
        return queryParams;
    }

    @Test
    public void getInterestedDevelopers()
    {
        String endpoint = "/user/REA";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParams());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
