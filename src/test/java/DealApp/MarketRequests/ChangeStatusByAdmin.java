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

public class ChangeStatusByAdmin extends BaseTest
    {

    @Test( priority = 1, dependsOnMethods ={
            "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest",
            "DealApp.ADS.CreateAd.createAdBySavedRea"})
    public void changeStatusByAdmin_ToApprove()
    {
        System.out.println(CreateMarketingRequest.marketRequestId);
        String endpoint = "/marketing-requests/admin/" + CreateMarketingRequest.marketRequestId;
        Map<String, Object> requestBody = Map.of("status", "APPROVED");
        Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "not null");
    }

    @Test( priority = 2, dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void changeStatusByAdmin_ToClosed()
    {
        String endpoint = "/marketing-requests/admin/" + CreateMarketingRequest.marketRequestId;
        Map<String, Object> requestBody = Map.of("status", "CLOSED", "closureReason", "NOT_NEEDED");
        Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "not null");
    }

    @Test ( priority = 3, dependsOnMethods = "DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest")
    public void changeStatusByAdmin_ToReadyForReview()
    {
        String endpoint = "/marketing-requests/admin/" + CreateMarketingRequest.marketRequestId;
        Map<String, Object> requestBody = Map.of("status", "READY_FOR_REVIEW");
        Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "not null");
    }
}

