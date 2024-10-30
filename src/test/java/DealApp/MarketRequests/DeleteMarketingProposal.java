package DealApp.MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

public class DeleteMarketingProposal extends RestAssuredUtilities
{
    @Test(dependsOnMethods = {
            "DealApp.MyAccount.REA.Rea.reaRegister",
            "DealApp.MyAccount.REA.Rea.reaRequestOTP",
            "DealApp.MyAccount.REA.Rea.getOTP",
            "DealApp.MyAccount.REA.Rea.reaEnterOTP",
            "DealApp.ADS.CreateAd.createAd",
            "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest",
            "DealApp.MarketRequests.CreateMarketingProposal.createMarketingProposal",
            "DealApp.MarketRequests.ChangeStatusByAdmin.changeStatusByAdmin_ToApprove"

    })
    public void deleteMarketingProposal() throws IOException
    {
        String endpoint="/marketing-proposals/"+CreateMarketingProposal.mkProposalID;
        Map<String,Object> requestBody=Map.of("status","DELETED");
        Response response=RestAssuredUtilities.performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("succuss"),"true");

    }
}
