package LoyaltySystem.PerofrmActions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.util.Map;

public class publishLicensedAdToGetPoints extends RestAssuredUtilities
    {

    @Test
    public void publishLicensedAdToGetPoints() throws IOException, InterruptedException
        {
            Map<String, Object> queryParam = Map.of("page", "1", "published", false);
            String endpoint = "/ad/mine";
            Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), queryParam);
            String adId = response.jsonPath().getString("data.id");
            Assert.assertEquals(response.statusCode(), 200);
        }
    }
