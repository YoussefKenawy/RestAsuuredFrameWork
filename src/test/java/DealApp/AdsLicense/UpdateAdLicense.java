package DealApp.AdsLicense;

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

public class UpdateAdLicense extends RestAssuredUtilities
    {
    public static String uniqueCouponText;

    public static String generateRandomKsaPhoneNumber()
        {
            Random random = new Random();
            StringBuilder phoneNumber = new StringBuilder("+9665");
            for (int i = 1; i <= 8; i++)
                {
                    phoneNumber.append(random.nextInt(9));  // Append a random digit (0-9)
                }
            return phoneNumber.toString();
        }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea", "DealApp.AdsLicense.CreateLicense.addLicense", "DealApp.AdsLicense.CreateLicense.upgradeLicense", "DealApp.AdsLicense.CreateLicense.addLicenseForOwner"})
    public void updateLicenseForOwner() throws IOException
        {
            String endpoint = "/ad-license-request/" + CreateLicense.licenseId;
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/AdsLisence/addLicenseForOwner.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"ownerPhone\": \"+966100000001\"", "\"ownerPhone\": \"" + generateRandomKsaPhoneNumber() + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Updated At must not be bull");
        }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea", "DealApp.AdsLicense.CreateLicense.addLicense", "DealApp.AdsLicense.CreateLicense.upgradeLicense", "DealApp.AdsLicense.CreateLicense.addLicenseForAttorney"})
    public void updateLicenseForAttorney() throws IOException
        {
            String endpoint = "/ad-license-request/" + CreateLicense.licenseId;
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/AdsLisence/addLicenseForAttorney.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"ownerPhone\": \"+966100000001\"", "\"ownerPhone\": \"" + generateRandomKsaPhoneNumber() + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Updated At must not be bull");
        }

    @Test(dependsOnMethods = {"DealApp.ADS.CreateAd.createAdBySavedRea",  "DealApp.AdsLicense.CreateLicense.addLicense", "DealApp.AdsLicense.CreateLicense.upgradeLicense", "DealApp.AdsLicense.CreateLicense.addLicenseForEntity"})

    public void updateLicenseForEntity() throws IOException
        {
            String endpoint = "/ad-license-request/" + CreateLicense.licenseId;
            String requestBodyJson = new String(Files.readAllBytes(Paths.get("src/test/dealResources/stagingEnv/AdsLisence/addLicenseForEntity.json")), StandardCharsets.UTF_8);
            // Replace the static adId in the JSON string with the dynamic adId from CreateAd
            requestBodyJson = requestBodyJson.replace("\"ownerPhone\": \"+966100000001\"", "\"ownerPhone\": \"" + generateRandomKsaPhoneNumber() + "\"");
            Map<String, Object> requestBody = new ObjectMapper().readValue(requestBodyJson, new TypeReference<Map<String, Object>>()
                {
                });

            Response response = performPatch(endpoint, Tokens.getInstance().getReaToken(), requestBody, sendHeaders());
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Updated At must not be bull");
        }

    }
