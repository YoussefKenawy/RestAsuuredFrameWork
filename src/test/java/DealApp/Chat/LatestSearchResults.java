package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class LatestSearchResults extends RestAssuredUtilities {
    @Test(dependsOnMethods =
            {
                    "DealApp.Chat.CreateNewChatRoom.createNewChatRoom",
                    "DealApp.Chat.AddChatSearchRecord.addChatSearchRecord",
                    "DealApp.Chat.SearchInChat.getLastSearchOfChat"

            })
    public void latestSearchResults() throws InterruptedException {
        String endpoint = "/chat/search-records/latest";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("messages[0]"), "hi");
    }
}
