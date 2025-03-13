package LoyaltySystem.PrepareLoyaltySettings;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class setLoyltyActions extends RestAssuredUtilities
    {

        @Test
        public static void setActionPoints()
        {
            // String USES_THE_APP_FOR_A_DAY = "67866f5070b30f896836ddc0";
            //String SUBMIT_THE_INTERESTS = "67866f5070b30f896836ddc1";
            // String COMMUNICATED_WITH_REQUESTS = "67866f5070b30f896836ddbc";
            //String PUBLISHED_ACCEPTED_LICENSE_AD = "67866f5070b30f896836ddbd";
            //String  PUBLISHED_REQUEST= "67866f5070b30f896836ddbe";
            //String DOCUMENTED_DONE_DEAL_INSIDE_DEAL = "67866f5070b30f896836ddbf";
            // String GET_POSITIVE_REVIEW = "678af5b0f6ebd06412242eb8";
            String[] actions = {"67866f5070b30f896836ddc0", "67866f5070b30f896836ddc1", "67866f5070b30f896836ddbc","67866f5070b30f896836ddbd", "67866f5070b30f896836ddbe", "67866f5070b30f896836ddbf", "678af5b0f6ebd06412242eb8"};
            Map<String, Object> requestBody = Map.of("enabled", "true", "scope", "UNLIMITED","points","10");
            for (int i = 1; i <= actions.length - 1; i++)
                {
                    String endpoint = "/loyalty/actions/admin/" + actions[i];
                    performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
                }
        }

        @Test
        public void getProducts()
        {
            String endpoint = "/loyalty/actions";
            Response response = performGet(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.jsonPath().getString("total"), "7");
            Assert.assertEquals(response.statusCode(), 200);
        }

    }
