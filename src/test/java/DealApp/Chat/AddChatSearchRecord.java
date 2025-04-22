package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;

public class AddChatSearchRecord extends RestAssuredUtilities

{
    public String chatRoom;
    @Test(dependsOnMethods = "DealApp.Chat.CreateNewChatRoom.createNewChatRoom")
    public void addChatSearchRecord() {
        String endpoint = "/chat/search-records";
        Map<String, Object> requestBody = Map.of
                (
                        "type", "CHAT_MESSAGE",
                        "message", "hi"
                );
        Response response = performPost(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 201);
    }
}
