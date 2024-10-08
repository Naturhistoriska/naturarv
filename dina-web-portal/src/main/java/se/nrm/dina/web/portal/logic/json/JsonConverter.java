package se.nrm.dina.web.portal.logic.json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

/**
 *
 * @author idali
 */
public class JsonConverter {

    private JsonReader jsonReader;
    private final String facets = "facets";
    private final String geohash = "geohash";
    private final String buckets = "buckets";

    private static JsonConverter instance = null;

    public static JsonConverter getInstance() {
        synchronized (JsonConverter.class) {
            if (instance == null) {
                instance = new JsonConverter();
            }
        }
        return instance;
    }

    public JsonArray buildResponseJson(String jsonString) {
        jsonReader = Json.createReader(new StringReader(jsonString));
        return jsonReader.readObject().getJsonObject(facets)
                .getJsonObject(geohash).getJsonArray(buckets);
    }
}
