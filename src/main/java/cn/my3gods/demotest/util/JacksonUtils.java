package cn.my3gods.demotest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * <br>Jackson的json工具</br>
 * 可配合实体属性上的Jackson注解
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/28 16:43
 */
@Slf4j
public class JacksonUtils {

    // 一个线程一个ObjectMapper提高效率，同时工具类创建好ObjectMapper对象避免每次使用创建以提高效率
    private final static ThreadLocal<ObjectMapper> RESOURCE = ThreadLocal.withInitial(() -> {
        ObjectMapper objectMapper = new ObjectMapper();
        // 实体没有对应字段不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    });

    private JacksonUtils() {
    }

    /**
     * 将目标对象加息为JSON字符串
     *
     * @param object 目标对象
     * @return JSON字符串，异常时返回Null
     */
    public static String obj2Json(Object object) {
        try {
            return RESOURCE.get().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JacksonUtils.obj2Json Json parse Error:{}\n{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将JSON字符串解析为目标对象
     *
     * @param json JSON字符串
     * @param valueType 目标对象的Class对象
     * @param <T> 对象类型
     * @return 目标类对象，异常时返回Null
     */
    public static <T> T json2Obj(String json, Class<T> valueType) {
        try {
            return RESOURCE.get().readValue(json, valueType);
        } catch (IOException e) {
            log.error("JacksonUtils.json2Obj IO Error:{}\n{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将JSON数组字符串解析为ArrayList
     *
     * @param json JSON字符串
     * @param valueType List内存储的对象类型
     * @param <T> 对象类型泛型
     * @return ArrayList<T>，异常返回Null
     */
    public static <T> List<T> json2List(String json, Class<T> valueType) {
        ObjectMapper objectMapper = RESOURCE.get();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, valueType));
        } catch (IOException e) {
            log.error("JacksonUtils.json2ArrayList IO Error:{}\n{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将JSON数组字符串解析为Map(key为String类型)
     *
     * @param json JSON字符串
     * @return Map:key-String ,value-Object
     */
    public static Map<String, Object> json2Map(String json) {
        ObjectMapper objectMapper = RESOURCE.get();
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            log.error("JacksonUtils.json2Map IO Error:{}\n{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将Map转换为对象
     *
     * @param map Map
     * @param valueType 目标对象类型
     * @param <T> 对象类型泛型
     * @return 对象
     */
    public static <T> T map2Pojo(Map map, Class<T> valueType) {
        return RESOURCE.get().convertValue(map, valueType);
    }

    /**
     * 将目标对象转换成指定类型
     *
     * @param origin 原对象
     * @param toValueTypeRef 所需的结果类型
     * @param <T> 结果类型
     * @return 指定类型
     */
    public static <T> T convertData(Object origin, TypeReference<T> toValueTypeRef) {
        return RESOURCE.get().convertValue(origin, toValueTypeRef);
    }

    /**
     * 获取Jackson的ObjectMapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return RESOURCE.get();
    }

}
