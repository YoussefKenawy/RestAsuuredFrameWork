package DealApp.MarketRequests.MarketingProposals;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class UpdateMarketingProposal extends RestAssuredUtilities
{
    @Test
    public void updateMarketingProposal() throws IOException
    {
        String endpoint="/marketing-proposals/66dd9e2d3678d15d8b6fb9b8";
        Map<String,Object> requestBody= getJsonDataAsMap("/MarketingRequests/UpdateMarketingProposal.json");
        Response response=RestAssuredUtilities.performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("succuss"),"true");

    }
}
