package DealApp.Chat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

public class ListAllActiveRooms extends RestAssuredUtilities
{
    @Test
    public void listAllActiveRooms()
    {
        String endpoint="/rooms";
        Response response= performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(),200);
    }
}
