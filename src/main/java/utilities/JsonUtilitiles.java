package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtilitiles {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> getJsonDataAsMap(String jsonFieName) throws IOException {
        String jsonfile = System.getProperty("user.dir") + "/src/test/dealResources/stagingEnv" + jsonFieName;
       Map<String,Object> data= objectMapper.readValue(new File(jsonfile), new TypeReference<Map<String,Object>>() {});

        return data;
    }


}
