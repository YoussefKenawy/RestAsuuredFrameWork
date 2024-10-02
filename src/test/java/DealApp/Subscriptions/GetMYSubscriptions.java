package DealApp.Subscriptions;

import groovyjarjarantlr4.runtime.TokenSource;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class GetMYSubscriptions extends RestAssuredUtilities
{
    @Test
    public void getSubscriptionsForRea()
    {
        String endpoint="/subscribe-package/current";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertNotNull(response.jsonPath().getString("subscription._id")," Id should no be null");
        Assert.assertNotNull(response.jsonPath().getString("subscription.packageName")," Id should no be null");
        Assert.assertEquals(response.jsonPath().getString("subscription.status"),"ACTIVE");
    }
    @Test
    public void CheckForbiddenToClient()
    {
        String endpoint="/subscribe-package/current";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken());
        Assert.assertEquals(response.getStatusCode(),403);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 403 Forbidden");
    }

}
