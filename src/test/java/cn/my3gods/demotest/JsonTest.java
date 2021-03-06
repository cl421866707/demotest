package cn.my3gods.demotest;

import cn.my3gods.demotest.json.test.ComplexJsonObject;
import cn.my3gods.demotest.json.test.JsonObject;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 测试方法 输入需要测试的个数 例如输入10000 然后会对此进行测试10次，
 * 去掉时间最小的，和一个时间最大的，剩下的8次再来求平均值 得到的就是我们所要求得平均速度
 * 这里我们需要测试10个数据 1000个数据 10万个数据
 */
public class JsonTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String jacksonObjToStr(Object jsonObject) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(jsonObject);
    }

    public static String fastjsonObjToStr(Object jsonObject) {
        return JSON.toJSONString(jsonObject);
    }

    public static <T> T jacksonStrToObject(String str, Class<T> valueType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(str, valueType);
    }

    public static <T> T fastjsonStrToObject(String str, Class<T> valueType) {
        return JSON.parseObject(str, valueType);
    }

    /**
     * 传入数据的个数
     */
    public static List<Double> complete(int nums) throws Exception {
        Double[] jacksonTimes = new Double[10];
        Double[] fastjsonTimes = new Double[10];
        Double[] jacksonTimes1 = new Double[10];
        Double[] fastjsonTimes1 = new Double[10];
        List<Double> time = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Double> list = getTime(nums);
            jacksonTimes[i] = list.get(0);
            fastjsonTimes[i] = list.get(1);
            jacksonTimes1[i] = list.get(2);
            fastjsonTimes1[i] = list.get(3);
        }
        Arrays.sort(jacksonTimes);
        Arrays.sort(fastjsonTimes);
        Arrays.sort(jacksonTimes1);
        Arrays.sort(fastjsonTimes1);
        Double sum = 0.00;
        for (int i = 1; i < 9; i++) {
            sum += jacksonTimes[i];
        }
        time.add(sum / 8);
        sum = 0.00;
        for (int i = 1; i < 9; i++) {
            sum += fastjsonTimes[i];
        }
        time.add(sum / 8);
        sum = 0.00;
        for (int i = 1; i < 9; i++) {
            sum += jacksonTimes1[i];
        }
        time.add(sum / 8);
        sum = 0.00;
        for (int i = 1; i < 9; i++) {
            sum += fastjsonTimes1[i];
        }
        time.add(sum / 8);
        return time;
    }

    /**
     * 传入数据的个数
     *
     * @return list.get(0)是jackson本次的时间 list.get(1)是fastjson本次的时间
     */
    public static List<Double> getTime(int nums) throws Exception {
        Double jacksonTime1 = 0.00;
        Double fastjsonTime1 = 0.00;
        Double jacksonTime2 = 0.00;
        Double fastjsonTime2 = 0.00;
        for (int i = 0; i < nums; i++) {
            //这里我们生成jsonObject
            JsonObject jsonObject = new JsonObject();
            long start, end;
            String str = null;
            //jackson：Object -> String
            start = System.currentTimeMillis();
            str = jacksonObjToStr(jsonObject);
            end = System.currentTimeMillis();
            jacksonTime1 += (double) (end - start);
            //fastjson：Object -> String
            start = System.currentTimeMillis();
            str = fastjsonObjToStr(jsonObject);
            end = System.currentTimeMillis();
            fastjsonTime1 += (double) (end - start);
            //jackson：String -> Object
            start = System.currentTimeMillis();
            jacksonStrToObject(str, JsonObject.class);
            end = System.currentTimeMillis();
            jacksonTime2 += (double) (end - start);
            //fastjson：String -> Object
            start = System.currentTimeMillis();
            fastjsonStrToObject(str, JsonObject.class);
            end = System.currentTimeMillis();
            fastjsonTime2 += (double) (end - start);
        }
        List<Double> list = new ArrayList<>();
        list.add(jacksonTime1);
        list.add(fastjsonTime1);
        list.add(jacksonTime2);
        list.add(fastjsonTime2);
        return list;
    }

    @Test
    public void testPerformance() throws Exception {
        //输入测试个数，得到时间
        List<Double> list10 = complete(10);
        List<Double> list1000 = complete(1000);
        List<Double> list100000 = complete(100000);
        System.out.println("------------------------序列化时间比较----------------------------");
        System.out.println("测试数据为10的时候:jackson序列化时间:" + list10.get(0) + "ms | fastjson序列化时间" + list10.get(1) + "ms");
        System.out.println("测试数据为1000的时候:jackson序列化时间:" + list1000.get(0) + "ms | fastjson序列化时间" + list1000.get(1) + "ms");
        System.out.println("测试数据为100000的时候:jackson序列化时间:" + list100000.get(0) + "ms | fastjson序列化时间" + list100000.get(1) + "ms");
        System.out.println("------------------------反序列化时间比较----------------------------");
        System.out.println("测试数据为10的时候:jackson反序列化时间:" + list10.get(2) + "ms | fastjson反序列化时间" + list10.get(3) + "ms");
        System.out.println("测试数据为1000的时候:jackson反序列化时间:" + list1000.get(2) + "ms | fastjson反序列化时间" + list1000.get(3) + "ms");
        System.out.println("测试数据为100000的时候:jackson反序列化时间:" + list100000.get(2) + "ms | fastjson反序列化时间" + list100000.get(3) + "ms");
    }

    @Test
    public void testObjectToJson() throws JsonProcessingException {
        Object jsonObject = new ComplexJsonObject();
        if (null == jsonObject) {
            jsonObject = new JsonObject();
        }
        System.err.println("jackson：" + jacksonObjToStr(jsonObject));
        System.err.println("fastjson：" + fastjsonObjToStr(jsonObject));
    }

    @Test
    public void testJsonToObject() throws JsonProcessingException {
        Object jsonObject = new ComplexJsonObject();
        if (null == jsonObject) {
            jsonObject = new JsonObject();
        }
        String jacksonStr = jacksonObjToStr(jsonObject);
        String fastjsonStr = fastjsonObjToStr(jsonObject);

        System.err.println("jackson(jacksonStr)：" + jacksonStrToObject(jacksonStr, jsonObject.getClass()));
//        System.err.println("fastjson(jacksonStr)：" + fastjsonStrToObject(jacksonStr, jsonObject.getClass()));

        System.err.println("jackson(fastjsonStr)：" + jacksonStrToObject(fastjsonStr, jsonObject.getClass()));
//        System.err.println("fastjson(fastjsonStr)：" + fastjsonStrToObject(fastjsonStr, jsonObject.getClass()));
    }
}

