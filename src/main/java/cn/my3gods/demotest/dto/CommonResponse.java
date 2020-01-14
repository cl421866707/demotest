package cn.my3gods.demotest.dto;

import java.io.Serializable;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> implements Serializable {

    public static final String success = "0";

    public static final String fail = "1";

    private String code;

    private String timestamp;

    private String message;

    private T data;

    private CommonResponse() {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    private CommonResponse(String code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    private CommonResponse(String code, String message, @Nullable T data) {
        this(code, message);
        this.data = data;
    }

    public static <T> CommonResponse<T> ok(String message) {
        return new CommonResponse<>(success, message);
    }

    public static <T> CommonResponse<T> ok(String message, T data) {
        return new CommonResponse<>(success, message, data);
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<>(fail, message);
    }

    public static <T> CommonResponse<T> fail(String message, T data) {
        return new CommonResponse<>(success, message, data);
    }
}
