package cn.my3gods.demotest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/6 13:48
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseMetaDto {

    // 总数
    private Long total;

    // 当前页
    private Long page;

    // 每页数量
    private Long limit;

    // 总页数
    private Long totalPage;
}
