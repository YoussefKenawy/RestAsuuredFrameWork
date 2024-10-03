package Dashboard.Projects;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class CreateProject  extends  RestAssuredUtilities{

    @Test
    public void createProject() throws IOException
    {
        String endpoint = "/projects";
        Map<String, Object> requestBody = getJsonDataAsMap("/Projects/CreateProjectData.json");
        Response response = RestAssuredUtilities.performPost(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
        response.then().statusCode(201);  // Asserting the status code

    }

}
