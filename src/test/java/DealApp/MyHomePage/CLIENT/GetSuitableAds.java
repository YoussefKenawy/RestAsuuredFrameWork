package DealApp.MyHomePage.CLIENT;

import DealApp.MyAccount.CLIENT.Client;
import DealApp.Requests.CreateRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

import java.util.*;

public class GetSuitableAds extends RestAssuredUtilities {

    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "DealApp.Requests.InteractWithRequests.activateRequestsByNewClient"
    })
    public void getSuitableAds() {
        Map<String,Object>sendQueryParams=Map.of("page","1","requestId" , CreateRequest.requestIdByClient,"allDeveloperAds",false,"isPromoted",false);
        String endpoint = "/ad";
        Response response = performGet(endpoint, Client.clientToken,sendQueryParams);
      String expectedDistrictId = "5dd490b6bc2e91004242ee31";
        List<String> actualDistrictIds = response.jsonPath().getList("data.district._id");
        for (String districtId : actualDistrictIds) {
            Assert.assertEquals(districtId,expectedDistrictId, "Unexpected district found: " + districtId);
        }
        Assert.assertEquals(response.statusCode(),200);
    }


}
