package Projects;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import java.io.IOException;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class UpdateProject {

    @Test
    public  void updateProject() throws IOException {
        String endpoint = "https://api.dealapp.sa/staging/projects/669cdd0919b1b4793942fd64";
    String adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTE2MzhhNjVkYWQxNTNjYTJmMjYwYTQiLCJyb2xlIjoiQURNSU4iLCJzdGF0dXMiOiJBQ1RJVkUiLCJwaG9uZSI6Iis5NjY1MDA4NDQ2NjYiLCJuYW1lIjoi2YXYrdmF2K8g2KfZhNiu2KfZhNiv2YoiLCJpYXQiOjE3MDYwOTUxNDd9.mZyHWKJCPT7rzf3t0NKJuNCYS1NijJ3yBEACILucncQ";
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "accept", "application/json",
                "Connection", "keep-alive"
        );
        Map<String, Object> requestBody = getJsonDataAsMap("/Projects/UpdateProjectData.json");
        Response response = RestAssuredUtilities.performPatch(endpoint, adminToken, requestBody, headers);
        response.then().statusCode(200);  // Asserting the status code
        response.then().body("name", equalTo("updatedProjectName1"));  // Asserting specific fields in the response body
        response.then().body("updatedAt", notNullValue());

    }
}