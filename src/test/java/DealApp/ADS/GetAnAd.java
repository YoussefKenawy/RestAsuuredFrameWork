package DealApp.ADS;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import static DealApp.ADS.CreateAd.adId;

public class GetAnAd extends RestAssuredUtilities {

    @Test (dependsOnMethods = "DealApp.ADS.CreateAd.createAdBySavedRea")
    public void getAdById()
    {
        String endpoint = "/ad/"+ adId;
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("data.id"), adId );
    }

}
