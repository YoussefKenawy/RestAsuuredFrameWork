package DealApp.MyHomePage.CLIENT;

import DealApp.MyAccount.CLIENT.Client;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.*;

public class GetNearestAds extends RestAssuredUtilities {

    @Test (dependsOnMethods ={
            "DealApp.MyAccount.CLIENT.Client.getOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRequestOTP",
            "DealApp.MyAccount.CLIENT.Client.clientRegister",
            "DealApp.MyAccount.CLIENT.Client.clientEnterOTP",
            "DealApp.Requests.CreateRequest.createRequestByNewClient",
            "DealApp.Requests.InteractWithRequests.activateRequestsByNewClient"
    })
    public void getNearestAds() {
        Map<String,Object>sendQueryParams=Map.of("page","10","nearestAds",true);
        String endpoint = "/ad/my-matching-ads";
        Response response = performGet(endpoint, Client.clientToken,sendQueryParams);


        Set<String> expectedDistrictIds = new HashSet<>(Arrays.asList("5dd490b6bc2e91004242ee34", "5dd490b6bc2e91004242ee39", "5dd490b6bc2e91004242ee31"));
        List<String> actualDistrictIds = response.jsonPath().getList("data.district._id");
        for (String districtId : actualDistrictIds) {
            Assert.assertTrue(expectedDistrictIds.contains(districtId), "Unexpected district found: " + districtId);
        }
        Assert.assertEquals(response.statusCode(),200);
    }


}
