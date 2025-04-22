package DealApp.Offices;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static utilities.JsonUtilitiles.getJsonDataAsMap;
import static utilities.RestAssuredUtilities.*;

public class UpdateOffice extends BaseTest
    {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2UwMWM0YTYxMWZiZTgyZjA4ZmY4NGQiLCJyb2xlIjoiUkVBTF9TVEFURV9BR0VOVCIsInN0YXR1cyI6IkFDVElWRSIsInBob25lIjoiKzk2NjE0MTU1NzU3NSIsIm5hbWUiOiJ0ZXN0IiwiaWF0IjoxNzQyNzYxODY0fQ.222EyPy92w7zlcdHbKmXmvHKzI6bYhF8w_oz11tl0G8";
    String entityId;

    @Test
    public void getLicenseId()
        {
            String endpoint = "/user/profile";
            Response response = performGet(endpoint, token);
            entityId = response.jsonPath().getString("data.officeRequest._id");
        }

    @Test(dependsOnMethods = "getLicenseId")
    public void updateOfficeLogo() throws IOException, InterruptedException
        {
            String endpoint = "/offices/" + entityId;
            Map<String, Object> requestBody = getJsonDataAsMap("/Offices/updateOfficeLogo.json");
            Response response = performPatch(endpoint, token, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("status"),"PUBLISHED_NEED_REVIEW");
        }
    }
