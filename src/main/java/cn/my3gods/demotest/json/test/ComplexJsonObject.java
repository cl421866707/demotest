package cn.my3gods.demotest.json.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplexJsonObject implements Serializable {

    private Integer[] intArray;
    private String[] strArray;
    private JsonObject[] jsonObjects;
    private JsonObject jsonObject;

    private Boolean fieldBoolean;
    private Integer fieldInt;
    private Long fieldLong;
    private Double fieldDouble;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fieldDate;
    private String fieldStr;
    private List<String> fieldList;
    private Map<String, Object> fieldMap;

    public ComplexJsonObject() {
        Random random = new Random();

        fieldBoolean = random.nextBoolean();
        fieldInt = random.nextInt();
        fieldLong = random.nextLong();
        fieldDouble = random.nextDouble();
        fieldDate = new Date();

        fieldStr = DataBuilder.randomString();
        fieldList = DataBuilder.randomStringList();
        fieldMap = DataBuilder.randomMap();
        intArray = DataBuilder.randomIntArry();
        strArray = DataBuilder.randomStrArry();
        jsonObjects = DataBuilder.randomJsonObjects();
        jsonObject = new JsonObject();

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("intArray", intArray)
            .add("strArray", strArray)
            .add("jsonObjects", jsonObjects)
            .add("jsonObject", jsonObject)
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
