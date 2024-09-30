package DealApp.MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class Check_Interaction_WithMarketingRequst extends RestAssuredUtilities
{
    
    @Test(dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void close_MarketingRequest()
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("status","CLOSED","closureReason","NOT_NEEDED");
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }


    @Test(dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void Not_Receive_broker_offers_MarketingRequest( )
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("isReceivingProposals",false);
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }


    @Test(dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void Receive_broker_offers_MarketingRequest( )
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("isReceivingProposals",true);
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }
}