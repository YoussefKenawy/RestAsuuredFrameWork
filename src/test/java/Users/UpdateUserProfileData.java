package Users;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utilities.RestAssuredUtilities.*;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserProfileData {
    String endpoint = "https://api.dealapp.sa/staging/user/profile";
  private  String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTE2MzhhNjVkYWQxNTNjYTJmMjYwYTQiLCJyb2xlIjoiQURNSU4iLCJzdGF0dXMiOiJBQ1RJVkUiLCJwaG9uZSI6Iis5NjY1MDA4NDQ2NjYiLCJuYW1lIjoi2YXYrdmF2K8g2KfZhNiu2KfZhNiv2YoiLCJpYXQiOjE3MDYwOTUxNDd9.mZyHWKJCPT7rzf3t0NKJuNCYS1NijJ3yBEACILucncQ";


    @Test
    public void updateProfile(){
        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("","");
        requestBody.put("","");
        requestBody.put("","");
        requestBody.put("","");


        Response response= performPut(endpoint,bearerToken,requestBody, new HashMap<>());
        Assert.assertEquals(response.statusCode(), 200);
        //Assert.assertEquals(response.body().jsonPath().getString("data.name[0]"), "Youssef");
    }
}
