package DealApp.Requests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.util.Map;

public class AdvancedSearchForRequests extends RestAssuredUtilities {

    public static Map<String, Object> sendQueryParamsWithRequestTypeSerious(String requestType)
    {
        Map<String, Object> queryParams = Map.of
                (
                        "limit", "10",
                        "city", "6009d941950ada00061eeeab",
                        "requestType", requestType
                );
        return queryParams;
    }

    @Test
    public void Check_Advanced_SeriousRequests_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithRequestTypeSerious("SERIOUS"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    public static Map<String, Object> sendQueryParamsWithRequestTypeNormal(String requestType)
    {
        Map<String, Object> queryParams = Map.of("city", "6009d941950ada00061eeeab", "requestType", requestType);
        return queryParams;
    }

    @Test
    public void Check_Advanced_NormalRequests_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithRequestTypeNormal("NORMAL"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    public static Map<String, Object> sendQueryParamsWithCity()
    {
        Map<String, Object> queryParams = Map.of("city", "6009d941950ada00061eeeab");
        return queryParams;
    }

    @Test
    public void Check_Advanced_City_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithCity());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    public static Map<String, Object> sendQueryParamsWithPropertyCategory()
    {
        Map<String, Object> queryParams = Map.of("propertyCategory", "6307c64e45e4badb612be78a", "propertyType", "63091bcd45e4ba93412d5d25");
        return queryParams;
    }

    @Test
    public void Check_Advanced_PropertyCategoryAndType_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithPropertyCategory());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    public static Map<String, Object> sendQueryParamsWithPurposeType(String purpose)
    {
        Map<String, Object> queryParams = Map.of("purpose", purpose);
        return queryParams;
    }

    @Test
    public void Check_Advanced_RentPurpose_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithPurposeType("RENT"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    @Test
    public void Check_Advanced_BuyPurpose_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithPurposeType("BUY"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should npt be null ");
    }

    public static Map<String, Object> sendQueryParamsWithNeedFinancing()
    {
        Map<String, Object> queryParams = Map.of("needFunding", false);
        return queryParams;
    }

    @Test
    public void Check_Advanced_NeedFinancing_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithNeedFinancing());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data"), "should not be null ");
    }
    public static Map<String, Object> sendQueryParamsWithPriceType(String priceType)
    {
        Map<String, Object> queryParams = Map.of("priceType", priceType);
        return queryParams;
    }

    @Test
    public void Check_Advanced_PriceTypeIsSpecific_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithPriceType("SPECIFIC"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data[0].priceType"), "SPECIFIC");
    }
    @Test
    public void Check_Advanced_PriceTypeBasesOnMarket_Search_By_Rea()
    {
        String endpoint = "/request";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken(), sendQueryParamsWithPriceType("BASED_ON_MARKET"));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data[0].priceType"), "BASED_ON_MARKET");
    }
    @Test
    public void Check_Advanced_RequestID_Search_By_Rea()
    {
        String endpoint = "/request?code=362660";
        Response response = performGet(endpoint, Tokens.getInstance().getReaToken());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("data[0].priceType"), "BASED_ON_MARKET");
    }


}
