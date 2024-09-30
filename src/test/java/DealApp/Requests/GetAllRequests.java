package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetAllRequests extends RestAssuredUtilities {
    @Test
    public void getAllRequestsWithoutFilter()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
    }

}
