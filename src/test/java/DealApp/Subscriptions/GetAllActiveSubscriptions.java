package DealApp.Subscriptions;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetAllActiveSubscriptions extends RestAssuredUtilities
{
    @Test
    public void getAllActiveSubscriptionsForRea()
    {
        String endpoint="/subscribe-package";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
