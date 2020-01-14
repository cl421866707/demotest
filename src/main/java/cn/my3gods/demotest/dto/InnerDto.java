package cn.my3gods.demotest.dto;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InnerDto {

    @NotBlank(message = "paramStr can not be blank")
    private String paramStr;

    @NotNull(message = "paramBool can not be null")
    private Boolean paramBool;

    @Min(value = 5, message = "paramInt - min 5")
    @Max(value = 10, message = "paramInt - max 10")
    private Integer paramInt;

    @NotEmpty(message = "paramStrList can not be empty")
    private List<String> paramStrList;
}
