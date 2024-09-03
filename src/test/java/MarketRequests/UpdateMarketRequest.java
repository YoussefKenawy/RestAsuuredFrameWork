package MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import static utilities.JsonUtilitiles.*;

import java.io.IOException;
import java.util.Map;

public class UpdateMarketRequest extends RestAssuredUtilities {
    @Test
    public void addMarketRequest() throws IOException
    {
        String endpoint="/marketing-requests/659c065a960cec097004a5bb";
        Map<String,Object> requestBody= getJsonDataAsMap("/MarketingRequests/UpdateMarketRequest.json");
        Response response=RestAssuredUtilities.performPatch(endpoint, Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
