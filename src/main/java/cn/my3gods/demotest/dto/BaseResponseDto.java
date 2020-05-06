package cn.my3gods.demotest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

/**
 * <br>基本响应数据格式</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/6 11:39
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class BaseResponseDto<T> {

    protected static final String SUCCESS = "0";

    protected static final String FAIL = "1";

    /**
     * 数据码编码
     */
    private String code;
    /**
     * 数据码信息
     */
    private String msg;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 数据值
     */
    private T data;
    /**
     * 数据概览/分页信息
     */
    private BaseMetaDto meta;

    private BaseResponseDto() {
        this.timestamp = System.currentTimeMillis();
    }

    private BaseResponseDto(String code, String msg, T data) {
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private BaseResponseDto(String code, String msg, T data, BaseMetaDto meta) {
        this(code, msg, data);
        this.meta = meta;
    }

    public static <T> BaseResponseDto<T> success() {
        return new BaseResponseDto<>(SUCCESS, null, null);
    }

    public static <T> BaseResponseDto<T> success(T data) {
        return new BaseResponseDto<>(SUCCESS, null, data);
    }

    public static <T> BaseResponseDto<T> success(String msg) {
        return new BaseResponseDto<>(SUCCESS, msg, null);
    }

    public static <T> BaseResponseDto<T> success(String msg, T data) {
        return new BaseResponseDto<>(SUCCESS, msg, data);
    }

    public static <T> BaseResponseDto<T> success(String msg, T data, BaseMetaDto baseMetaDto) {
        return new BaseResponseDto<>(SUCCESS, msg, data, baseMetaDto);
    }

    public static <T> BaseResponseDto<T> fail(String msg) {
        return new BaseResponseDto<>(FAIL, msg, null);
    }

    public static <T> BaseResponseDto<T> fail(String msg, T data) {
        return new BaseResponseDto<>(FAIL, msg, data);
    }

    public static <T> BaseResponseDto<T> response(String code, String msg, T data, BaseMetaDto baseMetaDto) {
        return new BaseResponseDto<>(code, msg, data, baseMetaDto);
    }

}
