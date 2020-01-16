package cn.my3gods.demotest.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {

    @ExcelProperty(value = {"学生信息表", "id序号"}, index = 0)
    private Long id;
    @ExcelProperty(value = {"学生信息表", "学号"}, index = 1)
    private String stuNo;
    @ExcelProperty(value = {"学生信息表", "姓名"}, index = 2)
    private String stuName;
    @ExcelProperty(value = {"学生信息表", "性别"}, index = 3)
    private String stuSex;
    @ExcelProperty(value = {"学生信息表", "年龄"}, index = 4)
    private Integer stuAge;
    @ExcelProperty(value = {"学生信息表", "学校"}, index = 5)
    private String stuSchool;
    @ExcelProperty(value = {"学生信息表", "城市"}, index = 6)
    private String cityName;
}
