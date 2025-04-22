package LoyaltySystem.RedeemProductsWithLoyaltyPoints;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static DealApp.Requests.CreateRequest.requestIdBySavedRea;

public class UpgradeSeriousRequestBuyUsingPoints extends RestAssuredUtilities
    {
        @Test (dependsOnMethods = "DealApp.ADS.CreateAd.createAdBySavedRea")
    public  void redeemPointForUpgradeSeriousRequestBuy()
            {

                String endpoint="/request/"+requestIdBySavedRea+"/upgrade";
                Map<String,Object> requestBody=Map.of("paymentMethod","LOYALTY_CREDIT","requestType","SERIOUS");
                Response response=performPatch(endpoint, Tokens.getInstance().getReaToken(),requestBody,sendHeaders());
                Assert.assertEquals( response.statusCode(),200);
            }
    }
