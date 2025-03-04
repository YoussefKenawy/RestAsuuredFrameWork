package DealApp.MarketRequests;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static utilities.RestAssuredUtilities.performPatch;
import static utilities.RestAssuredUtilities.sendHeaders;

public class Check_Interaction_WithMarketingRequst extends BaseTest
{

    @Test(priority = 2, dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void close_MarketingRequest()
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("status","CLOSED","closureReason","NOT_NEEDED");
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }


    @Test(priority = 3, dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void Not_Receive_broker_offers_MarketingRequest( )
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("isReceivingProposals",false);
        Response response= performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }

@Test(priority =1, dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
public void Receive_broker_offers_MarketingRequest( )
    {
        String endpoint="/marketing-requests/"+CreateMarketingRequest.marketRequestId;
        Map<String,Object> requestBody=Map.of("isReceivingProposals",true);
        Response response= performPatch(endpoint,Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),"not null");
    }

}