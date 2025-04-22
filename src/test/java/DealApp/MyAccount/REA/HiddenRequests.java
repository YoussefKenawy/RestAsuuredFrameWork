package DealApp.MyAccount.REA;

import DealApp.Requests.CreateRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

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
    @Test( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequestsBySavedClient","DealApp.Requests.InteractWithRequests.Check_Hide_Requests"})
    public void getHiddenRequestsByRea() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToHidden=response.jsonPath().getString("data[0]._id");
    }

}
