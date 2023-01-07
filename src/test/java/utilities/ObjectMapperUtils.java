package utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {


    private static ObjectMapper mapper;

    static {
           mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json,Class<T> cls){

        T javaResult = null;

        try {
           javaResult =  mapper.readValue(json,cls);  // Birsey return edecek
        } catch (IOException e) {
            System.err.println("json datasi javaya donusturulemedi");
        }

        return javaResult;
    }
}
