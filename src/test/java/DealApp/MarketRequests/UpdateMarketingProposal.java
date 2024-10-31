package DealApp.MarketRequests;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.sendHeaders;

public class UpdateMarketingProposal extends BaseTest
{
@Test(dependsOnMethods = {
        "DealApp.ADS.CreateAd.createAd",
        "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest",
        "DealApp.MarketRequests.ChangeStatusByAdmin.changeStatusByAdmin_ToApprove",
        "DealApp.MarketRequests.CreateMarketingProposal.createMarketingProposal",
})
    public void updateMarketingProposal() throws IOException
    {
        String endpoint="/marketing-proposals/"+CreateMarketingProposal.mkProposalID;
        Map<String,Object> requestBody= getJsonDataAsMap("/MarketingRequests/UpdateMarketingProposal.json");
        Response response=RestAssuredUtilities.performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("succuss"),"true");

    }
}
