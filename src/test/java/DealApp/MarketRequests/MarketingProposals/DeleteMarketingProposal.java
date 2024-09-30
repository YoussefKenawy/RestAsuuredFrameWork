package DealApp.MarketRequests.MarketingProposals;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

public class DeleteMarketingProposal extends RestAssuredUtilities
{
    @Test
    public void deleteMarketingProposal() throws IOException
    {
        //id is the id for rea +96614119777
        String endpoint="/marketing-proposals/66ddae133678d15d8b6fd094";
        Map<String,Object> requestBody=Map.of("status","DELETED");
        Response response=RestAssuredUtilities.performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("succuss"),"true");

    }
}
