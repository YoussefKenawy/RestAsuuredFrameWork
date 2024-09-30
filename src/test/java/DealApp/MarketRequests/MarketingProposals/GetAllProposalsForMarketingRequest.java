package DealApp.MarketRequests.MarketingProposals;

import io.restassured.response.Response;
import org.apache.http.protocol.ResponseServer;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetAllProposalsForMarketingRequest extends RestAssuredUtilities
{
    @Test
    public void get_AllProposals_For_A_MarketingRequest()
    {
        String endpoint="/marketing-requests/66788ef2f9a62f0f9a5b29c4/other-proposals";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("data"));
    }
}
