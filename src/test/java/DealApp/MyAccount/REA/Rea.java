package DealApp.MyAccount.REA;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

public class Rea extends RestAssuredUtilities
    {
    static String token;
    static final String PREFIX = "+96611";
    static final int NUMBER_LENGTH = 7;
    static final Random RANDOM = new Random();
    static String phone;
    public static String reaToken;
    public static String reaId;

    static final String ID_PREFIX = "11";


    public static String generateRandomKSAPhoneNumber()
        {
            StringBuilder phoneNumber = new StringBuilder(PREFIX);

            for (int i = 1; i <= NUMBER_LENGTH; i++)
                {
                    // Generate a random digit from 0 to 9
                    int randomDigit = RANDOM.nextInt(10);
                    phoneNumber.append(randomDigit);
                }
            String generatedNumber = phoneNumber.toString();
            System.out.println("Generated Random Phone Number: " + generatedNumber); // Debug statement
            return generatedNumber;
        }

    @Test(ignoreMissingDependencies = true)
    public void reaRegister() throws IOException
        {
            String endpoint = "/user/register";

            // Step 1: Read JSON file as a string
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/Users/reaRegistrationData.json")), StandardCharsets.UTF_8);


            // Print the original request body for debugging
            System.out.println("Original Request Body: " + requestBodyJson);

            // Step 3: Replace the phone number in the JSON string
            requestBodyJson = requestBodyJson.replace("\"phone\": \"+966011127452\"", "\"phone\": \"" + generateRandomKSAPhoneNumber() + "\"");
            // Step 4: Convert JSON string to Map
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            // Step 5: Perform the POST request
            Response response = performPost(endpoint, requestBody, sendHeaders());

            // Assertions
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("data.role"), "REAL_STATE_AGENT");
            phone = response.jsonPath().getString("data.phone");
            System.out.println("Phone number from registration body is" + phone);// Use the randomly generated phone number directly
        }

    @Test(dependsOnMethods = "reaRegister")
    public void reaRequestOTP()
        {
            String endpoint = "/user/login";
            Map<String, Object> requestBody = Map.of("phone", phone);
            Response response = performPost(endpoint, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("data.message"), "please verify with OTP sent to your phone");
        }

    @Test(dependsOnMethods = {"reaRequestOTP", "reaRegister"})
    public void getOTP()
        {
            String endpoint = "/user/otp";
            Map<String, Object> requestBody = Map.of("phone", phone);
            Response response = performPost(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            token = response.jsonPath().getString("data.otp");
            Assert.assertNotNull(token, "OTP MUST NOT BE NULL");
        }

    @Test(dependsOnMethods = {"getOTP", "reaRequestOTP", "reaRegister"})
    public void reaEnterOTP()
        {
            String endpoint = "/user/verify";
            Map<String, Object> requestBody = Map.of("phone", phone, "token", token);
            Response response = performPost(endpoint, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("data.role"), "REAL_STATE_AGENT");
            reaToken = response.getHeader("Authorization");
            reaId = response.jsonPath().getString("data._id");
            System.out.println(reaToken);
            System.out.println(reaId);

        }

    @Test(dependsOnMethods = {"getOTP", "reaRequestOTP", "reaRegister", "reaEnterOTP"})
    public void reaLogOut()
        {
            String endpoint = "/user/logout";
            Response response = performPost(endpoint, reaToken, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);

        }

    public static String generateRandomKSAIdentityNumber()
        {
            StringBuilder id = new StringBuilder(ID_PREFIX);

            for (int i = 1; i <= NUMBER_LENGTH; i++)
                {
                    int randomDigit = RANDOM.nextInt(10);
                    id.append(randomDigit);
                }
            String generatedNumber = id.toString();
            System.out.println("Generated Random Phone Number: " + generatedNumber); // Debug statement
            return generatedNumber;
        }

    public static Map<String, Object> sendQueryParams()
        {
            Map<String, Object> queryParams = Map.of("testMode", true);
            return queryParams;
        }

    @Test(dependsOnMethods = {"getOTP", "reaRequestOTP", "reaRegister", "reaEnterOTP"})
    public void authorizeWithNafaz() throws IOException
        {
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/Users/nafazDataVerfication.json")), StandardCharsets.UTF_8);
            requestBodyJson = requestBodyJson.replace("\"nationalId\": \"1234567890\"", "\"nationalId\": \"" + generateRandomKSAIdentityNumber() + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            String endpoint = "/user-verifications";
            Response response = performPost(endpoint, reaToken, requestBody, sendQueryParams(), sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertNotNull(response.jsonPath().getString("random"), "Random should no be null");

        }

    @Test(dependsOnMethods = {"getOTP", "reaRequestOTP", "reaRegister", "reaEnterOTP", "authorizeWithNafaz"})

    public void authorizeWithFal() throws IOException
        {
            String endpoint = "/fal-licenses";
            Map<String, Object> requestBody = Map.of("imageUrl", "imageUrl:https://uploadsstaging.dealapp.sa/daf8c0dd-1b76-4502-9f08-fa3f35317725.webp");
            Response response = performPost(endpoint, reaToken, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("status"), "PENDING");

        }
    }

