package utilities;
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
    @Getter @Setter
    private String guestToken;
    @Getter @Setter
    private String AyaToken;

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
        Map<String, String> tokensMap = objectMapper.readValue(new File("C:\\Users\\Tech Planet\\IdeaProjects\\RestAsuuredFrameWork\\src\\main\\resources\\tokens.json"), Map.class);

        this.adminToken = tokensMap.get("adminToken");
        this.reaToken = tokensMap.get("reaToken");
        this.clientToken = tokensMap.get("clientToken");
        this.guestToken = tokensMap.get("guestToken");
        this.AyaToken = tokensMap.get("AyaToken");

    }
}
