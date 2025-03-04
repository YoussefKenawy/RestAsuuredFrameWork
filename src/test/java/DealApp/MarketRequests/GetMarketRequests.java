package DealApp.MarketRequests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;

import static DealApp.ADS.CreateAd.adIDByNewRea;
import  static   DealApp.MarketRequests.CreateMarketingRequest.marketRequestId;
import  static   DealApp.ADS.CreateAd.adId;



public class GetMarketRequests extends RestAssuredUtilities
    {

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea","DealApp.MarketRequests.CreateMarketingRequest.addMarketRequest","DealApp.MarketRequests.ChangeStatusByAdmin.changeStatusByAdmin_ToApprove"})
    public void getMarketRequests()
        {

            String endpoint="/marketing-requests/mine";
            Map<String, Object> sendQueryParams = Map.of("page", "1", "limit","10");
            Response response= RestAssuredUtilities.performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams);
            Assert.assertEquals(response.statusCode(),200);
            Assert.assertEquals(response.jsonPath().getString("data[0]._id"),marketRequestId,"marketRequestId should not be null");
            Assert.assertEquals(response.jsonPath().getString("data[0].ad._id"),adId,"ad id  should not be null");
        }
    }
