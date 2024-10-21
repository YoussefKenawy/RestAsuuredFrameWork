package LearningRestAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetMyReq extends RestAssuredUtilities {

    @Test
    public void getMyReq()

    {
       String endpoint= "/request/563730";
       Response response= performGet(endpoint, Tokens.getInstance().getAyaToken());
      //  String endpoint= "/request/364155";
      //  Response response= performGet(endpoint, Tokens.getInstance().getClientToken());
        Assert.assertEquals(response.statusCode(),200);
    }
}
