package Dashboard.Projects;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static utilities.JsonUtilitiles.*;

public class UpdateProject  extends RestAssuredUtilities{

    @Test
    public  void updateProject() throws IOException {
        String endpoint = "/projects/669cdd0919b1b4793942fd64";
        Map<String, Object> requestBody = getJsonDataAsMap("/Projects/UpdateProjectData.json");
        Response response = performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        response.then().statusCode(200);  // Asserting the status code
        response.then().body("updatedAt", notNullValue());

    }
}