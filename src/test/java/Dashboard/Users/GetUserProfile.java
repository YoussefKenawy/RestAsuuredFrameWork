/*
package Dashboard.Users;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;

import static utilities.RestAssuredUtilities.*;

public class GetUserProfile {
    String endpoint = "https://staging.dealapp.sa/api/user/profile";
    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGExMWJlMzZlYTY4OTcyNjYwODQzYjkiLCJyb2xlIjoiUkVBTF9TVEFURV9BR0VOVCIsInN0YXR1cyI6IkFDVElWRSIsInBob25lIjoiKzk2NjEyMzQ1Njc4OSIsIm5hbWUiOiIzNCIsImlhdCI6MTcyMTIyMzQ3M30.9-Pesz7_6wRAVEJOAENfZ7BUABMnbBjlsnG1D17QyEY";

    @Test
    public void getUserProfileData() {
       Response response= performGet(endpoint,bearerToken);
        Assert.assertEquals(performGet(endpoint,bearerToken).statusCode(), 200);
        Assert.assertEquals(response.getBody().asString(), response.asString());
    }

}

*/