package DealApp.MarketRequests.MarketingProposals;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateMarketingProposal  extends RestAssuredUtilities
{
    @Test
    public void createMarketingProposal() throws IOException
    {
        String endpoint="/marketing-proposals";
        Map<String,Object> requestBody= getJsonDataAsMap("/MarketingRequests/CreateMarketingProposal.json");
        Response response=RestAssuredUtilities.performPost(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(response.jsonPath().getString("status"),"PENDING");

    }
}
