package cn.my3gods.demotest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 9:29
 */
public class GenericMethod {

    /**
     * 无查询条件获取所有数据
     *
     * @param url 目标URL
     * @param toValueTypeRef 类型引用对象
     * @param <T> 结果类型
     * @return List<结果类型>
     */
    public static <T> T getItemsWithoutCondition(String url, TypeReference<T> toValueTypeRef) {
        return getItemsByCondition(url, null, toValueTypeRef);
    }

    /**
     * 从BigCommerce分页查询货品
     *
     * @param url 目标URL
     * @param page 当前页
     * @param limit 每页数量
     * @param toValueTypeRef 类型引用对象
     * @param <T> 结果类型
     * @return 结果类型
     */
    public static <T> T getItemsByPage(String url, int page, int limit, TypeReference<T> toValueTypeRef) {
        if (page < 1) {
            page = 1;
        }
        if (limit < 1) {
            limit = 1;
        }
        return getItemsByCondition(url, ImmutableMap.<String, String>builder()
            .put("page", Integer.toString(page))
            .put("limit", Integer.toString(limit))
            .build(), toValueTypeRef);
    }

    /**
     * 根据条件从BigCommerce查询 条件值包含特殊符号可能会导致问题
     *
     * @param url 目标URL
     * @param conditions GET查询条件Map:key-条件名;value-条件值
     * @param toValueTypeRef 类型引用对象
     * @param <T> 结果类型
     * @return 结果类型
     */
    public static <T> T getItemsByCondition(String url, Map<String, String> conditions, TypeReference<T> toValueTypeRef) {
        // 如果参数Map不为空，则拼接查询参数
        String finalUrl = Objects.isNull(conditions) || conditions.isEmpty() ? url : addUrlParams(url, conditions);
        // 构建参数对象，发送GET请求
        String resultStr = "";
//        SendBigCommerce.get(RequestData.builder()
//            .X_Auth_Token(xAuthToken)
//            .X_Auth_Client(xAuthClient)
//            .url(finalUrl)
//            .build());
        return parseResponseData(resultStr, toValueTypeRef);
    }

    public static <T> T getSingleItemByIdAndCondition(String url, String bcId, Map<String, String> conditions, TypeReference<T> toValueTypeRef) {
        // 如果参数Map不为空，则拼接查询参数
        String finalUrl = Objects.isNull(conditions) || conditions.isEmpty() ? url : addPathvariableAndUrlParams(url, Collections.singletonList(bcId), conditions);
        // 构建参数对象，发送GET请求
        String resultStr = "";
//        SendBigCommerce.get(RequestData.builder()
//            .X_Auth_Token(xAuthToken)
//            .X_Auth_Client(xAuthClient)
//            .url(finalUrl)
//            .build());
        return parseResponseData(resultStr, toValueTypeRef);
    }


    /**
     * 拼接GET请求的路径变量以及URL参数
     *
     * @param url URL
     * @param pathVariables 路径变量集合，拼接按集合内顺序
     * @param params URL变量MAP
     * @return 拼接后的URL
     */
    public static String addPathvariableAndUrlParams(String url, List<String> pathVariables, Map<String, String> params) {
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
        return addUrlParams(urlBuilder.toString(), params);
    }

    /**
     * 拼接请求的URL参数
     *
     * @param url URL
     * @param params 参数MAP
     * @return 拼接的URL
     */
    public static String addUrlParams(String url, Map<String, String> params) {
        // 校验参数
        if (StringUtils.isBlank(url) || params.isEmpty()) {
            return url;
        }
        boolean haveParam = url.contains("?");
        // 不包含参数
        if (!haveParam) {
            return concatParams(new StringBuilder(url).append("?"), params);
        }
        // 包含参数 且 最后一位为 "&" 符号
        if (url.lastIndexOf("&") == url.length() - 1) {
            return concatParams(new StringBuilder(url), params);
        }
        // 包含参数 且 最后一位不为 "&" 符号
        return concatParams(new StringBuilder(url).append("&"), params);
    }

    /**
     * 拼接URL参数字符串（如果参数包含某些特殊字符可能导致错误）
     *
     * @param urlBuilder StringBuilder
     * @param params 参数MAP
     * @return 拼接后参数的URL
     */
    private static String concatParams(StringBuilder urlBuilder, Map<String, String> params) {
        // 遍历参数Map拼接参数
        params.forEach((key, value) -> urlBuilder.append(key).append("=").append(value).append("&"));
        // 从尾部开始&的索引位置
        int lastCharIndex = urlBuilder.lastIndexOf("&");
        // 如果最后一位是&则去掉
        return lastCharIndex == urlBuilder.length() - 1 ? urlBuilder.deleteCharAt(lastCharIndex).toString() : urlBuilder.toString();
    }

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
//        ResponseData responseData = JacksonUtils.json2Obj(json, ResponseData.class);
//        if (Objects.isNull(responseData) || Objects.isNull(responseData.getData())) {
//            return null;
//        }
//        return JacksonUtils.convertData(responseData.getData(), toValueTypeRef);
        return JacksonUtils.convertData(null, toValueTypeRef);
    }
}
