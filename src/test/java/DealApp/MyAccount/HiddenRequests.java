package DealApp.MyAccount;

import DealApp.Requests.CreateRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.HashMap;
import java.util.Map;

public class HiddenRequests extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParams()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "10",
                        "interactionFilter", "REJECT",
                        "sortBy","rejectBy.at"
                );
        return queryParams;
    }
    @Test( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.Check_Hide_Requests"})
    public void getFavoriteRequests() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToFavorite=response.jsonPath().getString("data[0]._id");
        Assert.assertEquals(addedRequestToFavorite, CreateRequest.requestIdByClient);
    }

}
