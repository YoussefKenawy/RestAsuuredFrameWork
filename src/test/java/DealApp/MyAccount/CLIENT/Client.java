package DealApp.MyAccount.CLIENT;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestAssuredUtilities;
import utilities.Tokens;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

public class Client extends RestAssuredUtilities
    {
    static String token;
    static final String PREFIX = "+96611";
    static final int NUMBER_LENGTH = 7;
    static final Random RANDOM = new Random();
    static String phone;
    public static String clientToken;
    public static Boolean isClientCreated = false;
    public static String clientId;
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

    public  String clientHelperMethod() throws IOException
        {
            clientRegister();
            clientRequestOTP();
            getOTP();
            return clientEnterOTP();
        }

    public void clientRegister() throws IOException
        {
            String endpoint = "/user/register";
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/Users/clientRegistrationData.json")), StandardCharsets.UTF_8);
            System.out.println("Original Request Body: " + requestBodyJson);
            requestBodyJson = requestBodyJson.replace("\"phone\": \"+966011127452\"", "\"phone\": \"" + generateRandomKSAPhoneNumber() + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>() {});
            Response response = performPost(endpoint, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("data.role"), "CLIENT");
            phone = response.jsonPath().getString("data.phone");
        }

    public void clientRequestOTP()
        {
            String endpoint = "/user/login";
            Map<String, Object> requestBody = Map.of("phone", phone);
            Response response = performPost(endpoint, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("data.message"), "please verify with OTP sent to your phone");
        }

    public void getOTP()
        {
            String endpoint = "/user/otp";
            Map<String, Object> requestBody = Map.of("phone", phone);
            Response response = performPost(endpoint, Tokens.getInstance().getAdminToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            token = response.jsonPath().getString("data.otp");
            Assert.assertNotNull(token, "OTP MUST NOT BE NULL");
        }

    public String clientEnterOTP()
        {
            String endpoint = "/user/verify";
            Map<String, Object> requestBody = Map.of("phone", phone, "token", token);
            Response response = performPost(endpoint, requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("data.role"), "CLIENT");
            isClientCreated = true;
            clientId = response.jsonPath().getString("data._id");
            System.out.println(clientToken);
            System.out.println(clientId);
            return clientToken = response.getHeader("Authorization");
        }


    public void clientLogOut() throws InterruptedException
        {
            String endpoint = "/user/logout";
            Response response = performPost(endpoint, clientToken, sendHeaders());
            Thread.sleep(1000);
            Assert.assertEquals(response.statusCode(), 200);
            Thread.sleep(1000);
        }
    public void deleteUser()
        {
            String endpoint="/user/"+clientEnterOTP();
            Response response=performDelete(endpoint, Tokens.getInstance().getAdminToken());
            Assert.assertEquals(response.statusCode(),200);
        }
    }

