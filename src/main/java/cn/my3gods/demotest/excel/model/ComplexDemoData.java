package cn.my3gods.demotest.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplexDemoData {

    /*
    复杂标题
    */
    @ExcelProperty(value = {"主标题", "序号"}, index = 0)
    private Integer id;
    @ExcelProperty(value = {"主标题", "内容"}, index = 1)
    private String content;
    @ExcelProperty(value = {"主标题", "创建时间"}, index = 2)
    private Date createTime;

    public static List<ComplexDemoData> data() {
        List<ComplexDemoData> complexDemoDataList = new ArrayList<>(128);
        for (int i = 0; i < 128; i++) {
            complexDemoDataList.add(ComplexDemoData.builder().id(i + 1).content("时间戳：" + System.currentTimeMillis()).createTime(new Date()).build());
        }
        return complexDemoDataList;
    }
}
