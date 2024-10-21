package DealApp.MyAccount;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.io.IOException;
import java.util.Map;
import static utilities.JsonUtilitiles.getJsonDataAsMap;

public class UpdateMyInterst extends RestAssuredUtilities {
    @Test
    public void putMyInterest() throws IOException {
        String endpoint ="/user/profile";
        Map<String, Object> requestBody = getJsonDataAsMap("/MyAccount/PutMyInterest.json");
        Response response= performPut(endpoint, Tokens.getInstance().getReaToken(), requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("data.citiesOfInterest._id"),"[6009d941950ada00061eeeab]");

    }
}
