package Projects;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
//import utilities.TokensLoader;

import java.io.IOException;

public class GetAllProjectsData {
    @Test
    public void getAllProjects() throws IOException
    {
        String endpoint = "/projects";
        Response response = RestAssuredUtilities.performGet(endpoint,Tokens.getInstance().getAdminToken());
        Assert.assertEquals(response.statusCode(), 200);
    }
}

