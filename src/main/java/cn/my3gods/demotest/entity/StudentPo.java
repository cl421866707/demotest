package cn.my3gods.demotest.entity;

import cn.my3gods.demotest.util.JsonUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("student")
public class StudentPo {

    private Long id;
    private String stuNo;
    private String stuName;
    private String stuSex;
    private Integer stuAge;
    private String stuSchool;
    private Long cityId;

    @Override
    public String toString() {
        try {
            return JsonUtils.object2Json(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("stuNo", stuNo)
            .add("stuName", stuName)
            .add("stuSex", stuSex)
            .add("stuAge", stuAge)
            .add("stuSchool", stuSchool)
            .add("cityId", cityId)
            .toString();
    }
}
