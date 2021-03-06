package cn.my3gods.demotest.dto.bc;

import java.util.Map;
import lombok.Data;

/**
 * <br>BigCommerce响应,对应其V3版本接口</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/28 19:18
 */
@Data
public class BcResponseDataV3<T> {

    // 数据主体，多个时为JSON数组，单个为对象
    private T data;

    // 分页信息，查询多个时BigCommerce会返回:"pagination" - BcPagination
    private Map<String, BcPagination> meta;
}
