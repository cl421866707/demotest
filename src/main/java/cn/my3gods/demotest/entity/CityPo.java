package cn.my3gods.demotest.entity;

import cn.my3gods.demotest.util.JacksonUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("city")
public class CityPo {

    private Long id;
    private String name;

    @Override
    public String toString() {
        try {
            return JacksonUtils.obj2Json(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .toString();
    }
}
