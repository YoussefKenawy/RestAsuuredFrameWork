package Dashboard.Users;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.performPost;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Register {

    @Test
    public void registerUser() {
        String endpoint = "https://api.dealapp.sa/staging/user/register";
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "accept", "application/json",
                "Content-Encoding", "gzip",
                "Connection", "keep-alive"
        );

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Youssef");
        requestBody.put("phone", "+966123456789");
        requestBody.put("email", "aa1_ken123@gmail.com");
        requestBody.put("gender", "MALE");

        Response response = performPost(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    public void registerUserUsingJsonFile() throws IOException {
        String endpoint = "https://api.dealapp.sa/staging/user/register";
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "accept", "application/json",
                "Content-Encoding", "gzip",
                "Connection", "keep-alive"
        );

        Map<String, Object> requestBody = getJsonDataAsMap("/reaRegistrationData.json");
        Response response = performPost(endpoint, requestBody, headers);
        Assert.assertEquals(response.statusCode(), 201);
    }
}
