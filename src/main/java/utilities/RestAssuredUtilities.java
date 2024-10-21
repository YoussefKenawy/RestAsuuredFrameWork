package utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportsManager;

import java.util.Map;

public class RestAssuredUtilities {

    public static RequestSpecification getRequestSpecification(String endpoint, String token, Object requestBody, Map<String, String> headers)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .headers(headers)
                .auth()
                .oauth2(token).log().all()
                .when()
                .body(requestBody).log().all();
    }
    public static RequestSpecification getRequestSpecification4(String endpoint, String token, Object requestBody,Map<String, Object> queryParam, Map<String, String> headers)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .queryParams(queryParam)
                .headers(headers)
                .auth()
                .oauth2(token).log().all()
                .when()
                .body(requestBody).log().all();
    }
    public static RequestSpecification getRequestSpecification2(String endpoint, String token, Map<String, String> headers)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .headers(headers)
                .auth()
                .oauth2(token).log().all();

    }



    // Overload method to delete token for requests that does not need token
    public static RequestSpecification getRequestSpecification(String endpoint, Object requestBody, Map<String, String> headers)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .headers(headers)
                .when()
                .body(requestBody)
                .log().all();
    }




    public static RequestSpecification getRequestSpecification(String endpoint, String token, Map<String, Object> headers)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .headers(headers)
                .auth()
                .oauth2(token)
                .log().all();
    }
    public static RequestSpecification getRequestSpecification3(String endpoint, String token, Map<String, Object> queryParam)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .queryParams(queryParam)
                .auth()
                .oauth2(token)
                .log().all();
    }


    public static RequestSpecification getRequestSpecification(String endpoint, String token)
    {
        String baseurl = "https://api.dealapp.sa/staging";
        return RestAssured.given()
                .baseUri(baseurl + endpoint)
                .auth()
                .oauth2(token)
                .log().all();
    }


    private static void printRequestLogInReport(RequestSpecification requestSpecification)
    {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportsManager.logInfoDetails("Endpoint is: " + queryableRequestSpecification.getBaseUri());
        ExtentReportsManager.logInfoDetails("Method is: " + queryableRequestSpecification.getMethod());
        ExtentReportsManager.logInfoDetails("Headers are: ");
        ExtentReportsManager.printHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportsManager.logInfoDetails("Request body is ");
        ExtentReportsManager.logInfoDetails("Query Params are: " + queryableRequestSpecification.getQueryParams());
        ExtentReportsManager.logJson(queryableRequestSpecification.getBody());

    }
    // Overload method for GET requests since it does not have a request oo

    private static void printRequestLogInReportForGetRequests(RequestSpecification requestSpecification)
    {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportsManager.logInfoDetails("Endpoint is: " + queryableRequestSpecification.getBaseUri());
        ExtentReportsManager.logInfoDetails("Method is: " + queryableRequestSpecification.getMethod());
        ExtentReportsManager.logInfoDetails("Query Params are: " + queryableRequestSpecification.getQueryParams());
    }


    private static void printResponseLogInReport(Response response)
    {
        ExtentReportsManager.logInfoDetails("Response Status is: " + response.getStatusCode());
        ExtentReportsManager.logInfoDetails("Response Headers are ");
        ExtentReportsManager.printHeaders(response.getHeaders().asList());
        ExtentReportsManager.logInfoDetails("Response body is ");
        ExtentReportsManager.logJson(response.getBody().prettyPrint());
    }

    public static Response performPost(String endpoint, String token, Map<String, Object> requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performPost(String endpoint, String token, Map<String, Object> requestBody, Map<String, Object> queryParam, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification4(endpoint, token, requestBody,queryParam, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }


    public static Response performDelete(String endpoint, String token, Map<String, Object> requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.delete();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performDelete(String endpoint, String token)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token);
        Response response = requestSpecification.delete();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performDelete(String endpoint, String token, String requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.delete();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    // Overload method to delete token for requests that does not need token
    public static Response performPost(String endpoint, Map<String, Object> requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, requestBody, headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
    public static Response performPost(String endpoint, String token,Map<String,String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification2(endpoint, token,sendHeaders());
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }


    public static Response performPut(String endpoint, String token, Map<String, Object> requestBody, Map<String, String> headers)
    {

        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.put();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;

    }

    public static Response performPatch(String endpoint, String token, Map<String, Object> requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.patch();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPatch(String endpoint, String token)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token);
        Response response = requestSpecification.patch();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPatch(String endpoint, String token, String requestBody, Map<String, String> headers)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token, requestBody, headers);
        Response response = requestSpecification.patch();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }


    public static Response performGet(String endpoint, String token)
    {
        RequestSpecification requestSpecification = getRequestSpecification(endpoint, token);
        Response response = requestSpecification.get();
        printRequestLogInReportForGetRequests(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performGet(String endpoint, String token, Map<String, Object> queryParams)
    {
        RequestSpecification requestSpecification = getRequestSpecification3(endpoint, token, queryParams);
        Response response = requestSpecification.get();
        printRequestLogInReportForGetRequests(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Map<String, String> sendHeaders()
    {
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json; charset=utf-8",
                "accept", "application/json",
                "Connection", "keep-alive"
        );
        return headers;
    }

}
