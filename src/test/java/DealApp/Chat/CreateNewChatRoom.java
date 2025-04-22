package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class CreateNewChatRoom extends RestAssuredUtilities {
    public static String chatRoomId;

    @Test
    public void createNewChatRoom() {
        String endpoint = "/rooms";
        Map<String, Object> requestBody = Map.of("receiver", "64884aafeab003eb526fbdca");
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        chatRoomId = response.jsonPath().getString("data._id");
        Assert.assertEquals(response.statusLine(), "HTTP/1.1 201 Created");
    }
}
