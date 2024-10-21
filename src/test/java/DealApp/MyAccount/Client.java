package DealApp.MyAccount;
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

public class Client extends RestAssuredUtilities {
    static String token;
    static final String PREFIX = "+96611";
    static final int NUMBER_LENGTH = 7;
    static final Random RANDOM = new Random();
    static String phone;
    static String clientToken;

    public static String generateRandomKSAPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder(PREFIX);

        for (int i = 1; i <= NUMBER_LENGTH; i++) {
            // Generate a random digit from 0 to 9
            int randomDigit = RANDOM.nextInt(10);
            phoneNumber.append(randomDigit);
        }
        String generatedNumber = phoneNumber.toString();
        System.out.println("Generated Random Phone Number: " + generatedNumber); // Debug statement
        return generatedNumber;
    }

    @Test
    public void clientRegister() throws IOException {
        String endpoint = "/user/register";

        // Step 1: Read JSON file as a string
        String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/Users/clientRegistrationData.json")), StandardCharsets.UTF_8);


        // Print the original request body for debugging
        System.out.println("Original Request Body: " + requestBodyJson);

        // Step 3: Replace the phone number in the JSON string
        requestBodyJson = requestBodyJson.replace("\"phone\": \"+966011127452\"", "\"phone\": \"" + generateRandomKSAPhoneNumber() + "\"");
        // Step 4: Convert JSON string to Map
        Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>() {
        });

        // Step 5: Perform the POST request
        Response response = performPost(endpoint, requestBody, sendHeaders());

        // Assertions
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("data.role"), "CLIENT");
        phone = response.jsonPath().getString("data.phone");
        System.out.println("Phone number from registration body is" + phone);// Use the randomly generated phone number directly
    }

    @Test(dependsOnMethods = "clientRegister")
    public void clientRequestOTP() {
        String endpoint = "/user/login";
        // Directly use the full phone number (including +)
        Map<String, Object> requestBody = Map.of("phone", phone);
        Response response = performPost(endpoint, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data.message"), "please verify with OTP sent to your phone");
    }

    @Test(dependsOnMethods = {"clientRequestOTP", "clientRegister"})
    public void getOTP() {
        String endpoint = "/user/otp";
        Map<String, Object> requestBody = Map.of("phone", phone);
        Response response = performPost(endpoint,  Tokens.getInstance().getAdminToken(),requestBody,sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        token = response.jsonPath().getString("data.otp");
        Assert.assertNotNull(token, "OTP MUST NOT BE NULL");
    }

    @Test(dependsOnMethods = {"getOTP", "clientRequestOTP", "clientRegister"})
    public void clientEnterOTP() {
        String endpoint = "/user/verify";
        Map<String, Object> requestBody = Map.of("phone", phone, "token", token);
        Response response = performPost(endpoint, requestBody, sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data.role"), "CLIENT");
        clientToken = response.getHeader("Authorization");
        System.out.println(clientToken);
    }

    @Test(dependsOnMethods = {"getOTP", "clientRequestOTP", "clientRegister", "clientEnterOTP"})
    public void clientLogOut() {
        String endpoint = "/user/logout";
        Response response = performPost(endpoint,clientToken,sendHeaders());
        Assert.assertEquals(response.statusCode(), 200);

    }
}

