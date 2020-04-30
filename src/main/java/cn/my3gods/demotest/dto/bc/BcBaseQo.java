package cn.my3gods.demotest.dto.bc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <br>基本查询实体</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/29 16:44
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BcBaseQo {

    // 当前页
    private Integer page;

    // 每页数据量
    private Integer limit;
}
