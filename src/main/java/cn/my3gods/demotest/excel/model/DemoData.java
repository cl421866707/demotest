package cn.my3gods.demotest.excel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
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
public class DemoData {

    /*
    @ExcelProperty
    value为表格标题栏内容
    index为所属列，从0开始
     */
    @ExcelProperty(value = "字符串标题", index = 0)
    private String string;
    @ExcelProperty(value = "日期标题", index = 1)
    private Date date;

    @NumberFormat("#.##%")//格式化：百分比
    @ExcelProperty(value = "数字标题", index = 2)
    private Double doubleData;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")//格式化日期
    @ExcelProperty(value = "创建时间", index = 3)
    private Date createTime;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    /**
     * 填充10个数据
     *
     * @return List<DemoData>
     */
    public static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            Date now = new Date();
            data.setString("字符串" + i);
            data.setDate(now);
            data.setDoubleData(0.56);
            data.setCreateTime(now);
            list.add(data);
        }
        return list;
    }

}
