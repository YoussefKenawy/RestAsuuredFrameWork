package DealApp.MyAccount.CLIENT;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.io.IOException;
import java.util.Map;
import static utilities.JsonUtilitiles.getJsonDataAsMap;



public class EditOnProfileDataAsClient extends RestAssuredUtilities {
    @Test
    public void EditOnMyProfileDataAsClient() throws IOException {
        String endpoint = "/user/profile";
        Map<String, Object> requestBody = getJsonDataAsMap("/MyAccount/Client/EditOnMyProfileData.json");
        Response response = performPut(endpoint, Tokens.getInstance().getClientToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data.name"), "Youssef");
        Assert.assertEquals(response.jsonPath().getList("data.notificationMethods").get(0), "CHAT", "First notification method should be CHAT");

    }

}

