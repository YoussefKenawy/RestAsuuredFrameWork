package LoyaltySystem.PrepareLoyaltySettings;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class setLoyaltyProductsPoints extends RestAssuredUtilities
    {
    @Test
    public void setProductsPoints()
        {
            // String SERIOUS_REQUEST_BUY = "67914e460f4378e1122ad821";
            //String SERIOUS_REQUEST_RENT = "67914e460f4378e1122ad822";
            // String PROMOTED_AD = "67914e460f4378e1122ad823";
            //String PROFESSIONAL_ONE_YEAR = "67914e460f4378e1122ad824";
            //String PROFESSIONAL_ONE_MONTH = "67914e460f4378e1122ad825";
            //String PREMIUM_ONE_MONTH = "67914e460f4378e1122ad826";
            // String PREMIUM_ONE_YEAR = "67914e460f4378e1122ad827";
            String[] products = {"67914e460f4378e1122ad821", "67914e460f4378e1122ad822", "67914e460f4378e1122ad823", "67914e460f4378e1122ad824", "67914e460f4378e1122ad825", "67914e460f4378e1122ad826", "67914e460f4378e1122ad827",};
            Map<String, Object> requestBody = Map.of("enabled", "true", "redeemPoints", "1");
            for (int i = 1; i <= products.length - 1; i++)
                {
                    String endpoint = "/loyalty/products/admin/" + products[i];
                    performPatch(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
                }
        }

    @Test
    public void getProducts()
        {
            String endpoint = "/loyalty/products";
            Response response = performGet(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.jsonPath().getString("total"), "7");
            Assert.assertEquals(response.statusCode(), 200);
        }

    }
