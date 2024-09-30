package DealApp.MarketRequests.MarketingProposals;

import io.restassured.internal.proxy.RestAssuredProxySelector;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class InteractWithMarketingProposals extends RestAssuredUtilities
{
    @Test
    public void Reject_MarketingProposals()
    {
        String endpoint="/marketing-requests/66788ef2f9a62f0f9a5b29c4/interaction";
        Map<String,Object>requestBody=Map.of("type","REJECT","rejectionReason","OTHER","otherRejectionReason","no more reasons.");
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }
    @Test
    public void Read_MarketingProposals()
    {
        String endpoint="/marketing-requests/66788ef2f9a62f0f9a5b29c4/interaction";
        Map<String,Object>requestBody=Map.of("type","READ");
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("type"),"READ");
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }
    @Test
    public void Chat_MarketingProposals()
    {
        String endpoint="/rooms";
        Map<String,Object>requestBody=Map.of("receiver","5e1638a65dad153ca2f260a4");
        Response response= performPost(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertNotNull(response.jsonPath().getString("data.type"),"ONE_TO_ONE");
    }
}
