package DealApp.Chat;

import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class SearchInChat extends RestAssuredUtilities {


    Map<String,Object>sendQueryParam=Map.of("q","hi");
    @Test
    public void getLastSearchOfChat() {
        String endpoint = "/chat/search";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParam);
        Assert.assertEquals(response.statusCode(),200);
    }
}
