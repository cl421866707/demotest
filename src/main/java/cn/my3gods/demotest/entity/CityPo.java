package cn.my3gods.demotest.entity;

import cn.my3gods.demotest.util.JsonUtils;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
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
            return JsonUtils.object2Json(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .toString();
    }
}
