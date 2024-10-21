package DealApp.MyAccount;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;
import java.util.Map;

public class TermsAndConditions extends RestAssuredUtilities
{

    public static Map<String, Object> sendQueryParams(String subCategory,String whenSend)
    {
        Map<String, Object> queryParams = Map.of
                (
                        "page", "1",
                        "limit", "1",
                        "category", "policies",
                        "subCategory",subCategory,
                        "whenSend",whenSend

                );
        return queryParams;
    }
    @Test ()
    public void getPoliciesPaymentByRea()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams("POLICIES_PAYMENTS","POLICIES_PAYMENTS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_PAYMENTS]");

    }

    @Test ()
    public void getPoliciesPrivacyByRea()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams("POLICIES_PRIVACY","POLICIES_PRIVACY"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_PRIVACY]");
    }

    @Test ()
    public void getPoliciesCopyRightsByRea()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams("POLICIES_COPYRIGHTS","POLICIES_COPYRIGHTS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_COPYRIGHTS]");

    }
    @Test ()
    public void getTermsOfUsePoliciesBtRea()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams("POLICIES_TERMS_OF_USE","POLICIES_TERMS_OF_USE"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_TERMS_OF_USE]");

    }


    @Test ()
    public void getRegulationsPoliciesByRea()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getReaToken(),sendQueryParams("POLICIES_ADS_REGULATIONS","POLICIES_ADS_REGULATIONS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_ADS_REGULATIONS]");

    }



    @Test ()
    public void getPoliciesPaymentByClient()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams("POLICIES_PAYMENTS","POLICIES_PAYMENTS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_PAYMENTS]");

    }

    @Test ()
    public void getPoliciesPrivacyByClient()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams("POLICIES_PRIVACY","POLICIES_PRIVACY"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_PRIVACY]");

    }
    @Test ()
    public void getPoliciesCopyRightsByClient()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams("POLICIES_COPYRIGHTS","POLICIES_COPYRIGHTS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_COPYRIGHTS]");

    }
    @Test ()
    public void getTermsOfUsePoliciesByClient()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams("POLICIES_TERMS_OF_USE","POLICIES_TERMS_OF_USE"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_TERMS_OF_USE]");

    }
    @Test ()
    public void getRegulationsPoliciesByClient()
    {
        String endpoint="/content";
        Response response=performGet(endpoint, Tokens.getInstance().getClientToken(),sendQueryParams("POLICIES_ADS_REGULATIONS","POLICIES_ADS_REGULATIONS"));
        Assert.assertEquals(response.jsonPath().getString("data.subCategory"),"[POLICIES_ADS_REGULATIONS]");
    }
}
