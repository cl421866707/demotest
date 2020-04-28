package cn.my3gods.demotest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    // 一个线程一个ObjectMapper
    private final static ThreadLocal<ObjectMapper> RESOURCE = ThreadLocal.withInitial(ObjectMapper::new);

    private JsonUtils() {
    }

    public static String object2Json(Object object) throws JsonProcessingException {
        return RESOURCE.get().writeValueAsString(object);
    }

    public static <T> T json2Object(String json, Class<T> valueType) throws JsonProcessingException {
        return RESOURCE.get().readValue(json, valueType);
    }

    public static ObjectMapper getObjectMapper() {
        return RESOURCE.get();
    }
}
