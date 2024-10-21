package DealApp.MyAccount;

import DealApp.Requests.CreateRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;

public class MyClients extends RestAssuredUtilities
{
    public static Map<String, Object> sendQueryParamsForFavoriteRequests()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "10",
                        "interactionsType", "FAVORITE",
                        "sortBy", "favoriteBy.at"
                );
        return queryParams;
    }
    public static Map<String, Object> sendQueryParamsForCommunicatedRequests()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "10",
                        "interactionsType", "CHAT,CALL_PHONE,WHATSAPP"
                );
        return queryParams;
    }
    public static Map<String, Object> sendQueryParamsForReadRequests()
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "10",
                        "interactionsType", "READ",
                        "sortBy","readBy.at"
                );
        return queryParams;
    }

    @Test ( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.Check_FAVORITE_Interaction_WithRequestsByRea"})
    public void getFavoriteRequests() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParamsForFavoriteRequests());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToFavorite=response.jsonPath().getString("data[0]._id");
        Assert.assertEquals(addedRequestToFavorite, CreateRequest.requestIdByClient);
    }

    @Test ( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.Check_CALL_PHONE_Interaction_WithRequestsByRea"})
    public void getCommunicatedRequestsOfPhone() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParamsForCommunicatedRequests());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToFavorite=response.jsonPath().getString("data[0]._id");
        Assert.assertEquals(addedRequestToFavorite, CreateRequest.requestIdByClient);
    }
    @Test ( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.Check_WHATSAPP_Interaction_WithRequestsByRea"})
    public void getCommunicatedRequestsOfWhatsapp() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParamsForCommunicatedRequests());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToFavorite=response.jsonPath().getString("data[0]._id");
        Assert.assertEquals(addedRequestToFavorite, CreateRequest.requestIdByClient);
    }

    @Test ( dependsOnMethods ={ "DealApp.Requests.CreateRequest.createRequestByClient","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.activateRequests","DealApp.Requests.InteractWithRequests.Check_READ_Interaction_WithRequests"})
    public void getReadRequests() throws InterruptedException {
        Thread.sleep(2000);
        String endpoint="/request";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParamsForReadRequests());
        Assert.assertEquals(response.statusCode(),200);
        String addedRequestToFavorite=response.jsonPath().getString("data[0]._id");
        Assert.assertEquals(addedRequestToFavorite, CreateRequest.requestIdByClient);
    }


}
