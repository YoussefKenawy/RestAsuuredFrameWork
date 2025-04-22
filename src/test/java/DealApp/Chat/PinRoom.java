package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class PinRoom extends RestAssuredUtilities
{
    public String chatRoom;
    @Test(dependsOnMethods = "DealApp.Chat.CreateNewChatRoom.createNewChatRoom")
    public void pinRoom() {
        String endpoint = "/rooms/"+CreateNewChatRoom.chatRoomId;
        Map<String, Object> requestBody = Map.of("star", true);
        Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("isStarred"),"isStarred should be true");
    }
}
