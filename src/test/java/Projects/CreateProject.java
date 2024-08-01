package Projects;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateProject {

    @Test
    public void createProject() throws IOException
    {
        String endpoint = "https://api.dealapp.sa/staging/projects";
        String adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTE2MzhhNjVkYWQxNTNjYTJmMjYwYTQiLCJyb2xlIjoiQURNSU4iLCJzdGF0dXMiOiJBQ1RJVkUiLCJwaG9uZSI6Iis5NjY1MDA4NDQ2NjYiLCJuYW1lIjoi2YXYrdmF2K8g2KfZhNiu2KfZhNiv2YoiLCJpYXQiOjE3MDYwOTUxNDd9.mZyHWKJCPT7rzf3t0NKJuNCYS1NijJ3yBEACILucncQ";
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "accept", "application/json",
                "Connection", "keep-alive"
        );
        Map<String, Object> requestBody = getJsonDataAsMap("/Projects/CreateProjectData.json");
        Response response = RestAssuredUtilities.performPost(endpoint, adminToken, requestBody, headers);
        response.then().statusCode(201);  // Asserting the status code
        response.then().body("name", equalTo("مشروع9"));  // Asserting specific fields in the response body


    }

}
