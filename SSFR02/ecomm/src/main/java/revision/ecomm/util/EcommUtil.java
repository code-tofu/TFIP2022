package revision.ecomm.util;

import java.io.StringReader;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class EcommUtil {

    public static JsonObject getJSONObj(String jsonString){
        StringReader sr = new StringReader(jsonString.toString());
        JsonReader jsr = Json.createReader(sr);
        return (JsonObject)jsr.read();
    }

    public static String generateUUID(int numOfChar){ 
        return UUID.randomUUID().toString().substring(0,numOfChar);
    }
    
}
