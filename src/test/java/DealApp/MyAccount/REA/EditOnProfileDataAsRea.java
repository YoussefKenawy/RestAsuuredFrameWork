package DealApp.MyAccount.REA;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class EditOnProfileDataAsRea extends RestAssuredUtilities {
    @Test
    public void EditOnMyProfileDataAsRea() throws IOException {
        String endpoint = "/user/profile";
        Map<String, Object> requestBody = getJsonDataAsMap("/MyAccount/Rea/EditOnMyProfileData.json");
        Response response = performPut(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data.name"), "Youssef");
        Assert.assertEquals(response.jsonPath().getList("data.notificationMethods").get(0), "CHAT", "First notification method should be CHAT");
        Assert.assertEquals(response.jsonPath().getString("data.entity.name"), "MyCompany");
        Assert.assertEquals(response.jsonPath().getString("data.socialLinks.facebook"), "https://www.facebook.com/youssef-kenawy");
        Assert.assertEquals(response.jsonPath().getString("data.bio"), "newBio");

    }

}

