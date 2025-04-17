package DealApp.Offices;

import DealApp.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import static DealApp.MyAccount.REA.Rea.reaToken;
import static utilities.RestAssuredUtilities.performGet;

public class ShowOfficesInAgentsMap extends BaseTest
    {
        @Test
    public void showOfficesInAgentsMap()
            {
                String endpoint="/user/map-agents?officeVerificationStatus=PUBLISHED_NEED_REVIEW,APPROVED";
                Response response=performGet(endpoint, Tokens.getInstance().getReaToken());
                Assert.assertEquals(response.statusCode(),200);
                Assert.assertNotNull(response.jsonPath().getString("data"));
            }
    }
