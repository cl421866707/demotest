package cn.my3gods.demotest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;

public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static String object2Json(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    public static <T> T json2Object(String json, Class<T> valueType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, valueType);
    }
}
