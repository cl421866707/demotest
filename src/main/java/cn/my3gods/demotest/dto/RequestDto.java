package cn.my3gods.demotest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RequestDto<T> {

    @NotEmpty(message = "accessToken can not be empty")
    private String accessToken;

    @Valid
    private T data;
}
