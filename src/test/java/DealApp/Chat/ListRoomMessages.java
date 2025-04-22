package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class ListRoomMessages extends RestAssuredUtilities {
    @Test(dependsOnMethods =
            {
                    "DealApp.Chat.CreateNewChatRoom.createNewChatRoom",
            })
    public void listRoomMessages() throws InterruptedException {
        String endpoint = "/rooms/"+CreateNewChatRoom.chatRoomId;
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
