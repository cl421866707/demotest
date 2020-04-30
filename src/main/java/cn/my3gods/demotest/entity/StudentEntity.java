package cn.my3gods.demotest.entity;

import cn.my3gods.demotest.util.JacksonUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("student")
public class StudentEntity {

    private Long id;
    private String stuNo;
    private String stuName;
    private String stuSex;
    private Integer stuAge;
    private String stuSchool;
    private CityPo cityPo;

    @Override
    public String toString() {
        try {
            return JacksonUtils.obj2Json(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("stuNo", stuNo)
            .add("stuName", stuName)
            .add("stuSex", stuSex)
            .add("stuAge", stuAge)
            .add("stuSchool", stuSchool)
            .add("cityPo", cityPo)
            .toString();
    }
}
