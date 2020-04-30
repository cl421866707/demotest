package cn.my3gods.demotest.util;

import cn.my3gods.demotest.dto.bc.BcResponseDataV3;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 9:29
 */
public class GenericMethod {

    /**
     * 解析BigCommerce的响应数据，并获取所需的类型的data数据(根据结果类型推断要转换成什么类型数据)
     *
     * @param json JSON字符串
     * @param toValueTypeRef 类型引用对象
     * @param <T> 结果类型
     * @return 目标数据类型
     */
    public static <T> T parseResponseData(String json, TypeReference<T> toValueTypeRef) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        BcResponseDataV3 responseData = JacksonUtils.json2Obj(json, BcResponseDataV3.class);
        if (Objects.isNull(responseData) || Objects.isNull(responseData.getData())) {
            return null;
        }
        return JacksonUtils.convertData(responseData.getData(), toValueTypeRef);
    }

    /**
     * 按集合顺序拼接请求的路径变量
     *
     * @param url URL
     * @param pathVariables 路径变量集合
     * @return 拼接后的URL
     */
    public static String addPathVariable(String url, List<String> pathVariables) {
        if (StringUtils.isBlank(url) || CollectionUtils.isEmpty(pathVariables)) {
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        // URL最后一位是"/"就去掉
        if (url.lastIndexOf("/") == url.length() - 1) {
            urlBuilder.deleteCharAt(url.length() - 1);
        }
        // 拼接路径变量："url/123/32121"
        for (String pathvariable : pathVariables) {
            urlBuilder.append("/").append(pathvariable);
        }
        return urlBuilder.toString();
    }

    /**
     * 给URL添加路径变量和URL参数
     *
     * @param baseUrl 基础URL
     * @param pathVariables 路径变量
     * @param paramObj URL参数实体
     * @return 拼接后的URL字符串
     * @throws IllegalAccessException 反射操作中的异常
     */
    public static String addPathVariableAndUrlParams(String baseUrl, List<String> pathVariables, Object paramObj) throws IllegalAccessException {
        return addUrlParams(addPathVariable(baseUrl, pathVariables), paramObj);
    }

    /**
     * 给URL添加路径变量和URL参数
     *
     * @param baseUrl 基础URL
     * @param pathVariables 路径变量
     * @param paramMap URL参数Map
     * @return 拼接后的URL字符串
     */
    public static String addPathVariableAndUrlParams(String baseUrl, List<String> pathVariables, Map<String, String> paramMap) {
        return addUrlParams(addPathVariable(baseUrl, pathVariables), paramMap);
    }

    /**
     * 给URL添加URL参数
     *
     * @param baseUrl 基础URL
     * @param paramObj URL参数实体
     * @return 拼接后的URL字符串
     * @throws IllegalAccessException 反射操作中的异常
     */
    public static String addUrlParams(String baseUrl, Object paramObj) throws IllegalAccessException {
        if (StringUtils.isBlank(baseUrl)) {
            return baseUrl;
        }
        Field[] fields = paramObj.getClass().getDeclaredFields();
        if (0 == fields.length) {
            return baseUrl;
        }
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        for (Field field : fields) {
            // 私有属性可访问
            field.setAccessible(true);
            Object value = field.get(paramObj);
            if (Objects.nonNull(value)) {
                uriComponentsBuilder.queryParam(field.getName(), value);
            }
        }
        return uriComponentsBuilder.toUriString();
    }

    /**
     * 给URL添加URL参数
     *
     * @param baseUrl 基础URL
     * @param paramMap URL参数Map
     * @return 拼接后的URL字符串
     */
    public static String addUrlParams(String baseUrl, Map<String, String> paramMap) {
        if (StringUtils.isBlank(baseUrl)) {
            return baseUrl;
        }
        if (Objects.isNull(paramMap) || paramMap.isEmpty()) {
            return baseUrl;
        }
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        paramMap.forEach(uriComponentsBuilder::queryParam);
        return uriComponentsBuilder.toUriString();
    }

    /**
     * 将对象转换成Map
     *
     * @param object 目标对象
     * @return Map:key - 属性名,value - 属性值
     */
    public static Map<String, String> obj2Map(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        if (0 == fields.length) {
            return new HashMap<>(0);
        }
        Builder<String, String> mapBuilder = ImmutableMap.builder();
        for (Field field : fields) {
            // 私有属性可访问
            field.setAccessible(true);
            Object value = field.get(object);
            mapBuilder.put(field.getName(), Objects.nonNull(value) ? value.toString() : "");
        }
        return mapBuilder.build();
    }
}
