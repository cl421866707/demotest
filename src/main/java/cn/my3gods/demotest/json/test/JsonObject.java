package cn.my3gods.demotest.json.test;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

/**
 * 样本对象
 */
@Getter
@Setter
public class JsonObject implements Serializable {

    private static final long serialVersionUID = -1520171788566678009L;

    private Boolean fieldBoolean;
    private Integer fieldInt;
    private Long fieldLong;
    private Double fieldDouble;
    private Date fieldDate;
    private String fieldStr;
    private List<String> fieldList;
    private Map<String, Object> fieldMap;

    /**
     * 随机样本
     */
    public JsonObject() {
        Random random = new Random();

        fieldBoolean = random.nextBoolean();
        fieldInt = random.nextInt();
        fieldLong = random.nextLong();
        fieldDouble = random.nextDouble();
        fieldDate = new Date();

        fieldStr = DataBuilder.randomString();

        fieldList = DataBuilder.randomStringList();

        fieldMap = DataBuilder.randomMap();
    }

    /**
     * 指定List元素数量和Map元素数量的样本
     *
     * @param listSize List元素数量
     * @param mapKeyNum Map元素数量
     */
    public JsonObject(int listSize, int mapKeyNum) {
        Random random = new Random();

        fieldBoolean = random.nextBoolean();
        fieldInt = random.nextInt();
        fieldLong = random.nextLong();
        fieldDouble = random.nextDouble();
        fieldDate = new Date();

        fieldStr = DataBuilder.randomString();

        fieldList = DataBuilder.randomStringList(listSize);

        fieldMap = DataBuilder.randomMap(mapKeyNum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("fieldBoolean", fieldBoolean)
            .add("fieldInt", fieldInt)
            .add("fieldLong", fieldLong)
            .add("fieldDouble", fieldDouble)
            .add("fieldDate", fieldDate)
            .add("fieldStr", fieldStr)
            .add("fieldList", fieldList)
            .add("fieldMap", fieldMap)
            .toString();
    }
}
