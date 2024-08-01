package utilities;/*
package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Tokens {
    @Getter
    @Setter
    private String adminToken;
    @Getter
    @Setter
    private String reaToken;
    @Getter
    @Setter
    private String clientToken;
    private static final String TOKEN_FILE_PATH = "C:\\Users\\Youssef\\IdeaProjects\\RestAssuredFrameWork\\src\\test\\dealResources\\tokens.json";

    public static Tokens loadTokens() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> tokensMap = objectMapper.readValue(new File(TOKEN_FILE_PATH), Map.class);
        Tokens tokens = new Tokens();
        tokens.setAdminToken(tokensMap.get("adminToken"));
        tokens.setReaToken(tokensMap.get("reaToken"));
        tokens.setClientToken(tokensMap.get("clientToken"));
        return tokens;
    }
}*/
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Tokens {
    @Getter @Setter
    private String adminToken;
    @Getter @Setter
    private String reaToken;
    @Getter @Setter
    private String clientToken;

    private static Tokens instance;

    public static Tokens getInstance() {
        if (instance == null) {
            instance = new Tokens();
            try {
                instance.loadTokens();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private void loadTokens() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> tokensMap = objectMapper.readValue(new File("C:\\Users\\Youssef\\IdeaProjects\\RestAssuredFrameWork\\src\\main\\resources\\tokens.json"), Map.class);

        this.adminToken = tokensMap.get("adminToken");
        this.reaToken = tokensMap.get("reaToken");
        this.clientToken = tokensMap.get("clientToken");
    }
}
