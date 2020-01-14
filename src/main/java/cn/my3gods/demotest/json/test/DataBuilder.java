package cn.my3gods.demotest.json.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 该类提供生成样本的元数据
 */
public class DataBuilder {

    private DataBuilder() {
    }

    private static final String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
        "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
        "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z"};

    // 默认数组大小
    private static final int DEFAULT_ARRY_SIZE = 10;
    // 数字默认随机生成范围
    private static final int DEFAULT_ARRY_BOUND_NUM = 100;

    private static final int CHAR_NUM = 62;

    // 样本String最大长度
    private static final int MAX_STR_LENGTH = 100;

    // 样本String默认长度
    private static final int DEFAULT_STR_LENGTH = 50;

    // 样本List最大长度
    private static final int MAX_LIST_SIZE = 100;

    // 样本List默认长度
    private static final int DEFAULT_LIST_SIZE = 10;

    // 样本Map最大Key数量
    private static final int MAX_MAP_SIZE = 100;

    // 样本Map默认Key数量
    private static final int DEFAULT_MAP_SIZE = 10;

    // 样本Map中Value的数据类型
    private static final String[] TYPES = new String[]{"boolean", "int", "long", "double", "date", "string"};

    // 指定TYPES生成的数字在[0,6)之间
    private static final int TYPE_NUM = 6;

    private static final Random RANDOM = new Random();

    /**
     * 生成随机长度的字符串
     *
     * @return 字符串
     */
    public static String randomString() {
        return randomString(RANDOM.nextInt(MAX_STR_LENGTH));
    }

    /**
     * 生成指定长度的字符串
     *
     * @param len 字符串长度
     */
    public static String randomString(int len) {
        if (len < 1 || len > MAX_STR_LENGTH) {
            // 如果字符串长度超出范围，使用默认长度
            len = DEFAULT_STR_LENGTH;
        }

        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(chars[RANDOM.nextInt(CHAR_NUM)]);
        }

        return sb.toString();
    }

    /**
     * 生成List样本，List中元素的数量随机
     */
    public static List<String> randomStringList() {
        return randomStringList(RANDOM.nextInt(MAX_LIST_SIZE));
    }

    /**
     * 生成List样本
     *
     * @param size List中元素的数量
     */
    public static List<String> randomStringList(int size) {
        if (size < 1 || size > MAX_LIST_SIZE) {
            size = DEFAULT_LIST_SIZE;
        }

        List<String> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(randomString(RANDOM.nextInt(MAX_STR_LENGTH)));
        }

        return list;
    }

    /**
     * 生成随机Map样本，样本中key的数量随机
     */
    public static Map<String, Object> randomMap() {
        return randomMap(RANDOM.nextInt(MAX_MAP_SIZE));
    }

    /**
     * 生成随机Map样本
     *
     * @param size 样本中key的数量
     */
    public static Map<String, Object> randomMap(int size) {
        if (size < 1 || size > MAX_MAP_SIZE) {
            size = DEFAULT_MAP_SIZE;
        }

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String type = TYPES[RANDOM.nextInt(TYPE_NUM)];
            switch (type) {
                case "boolean":
                    map.put("key" + i, RANDOM.nextBoolean());
                    break;
                case "int":
                    map.put("key" + i, RANDOM.nextInt());
                    break;
                case "long":
                    map.put("key" + i, RANDOM.nextLong());
                    break;
                case "double":
                    map.put("key" + i, RANDOM.nextDouble());
                    break;
                case "date":
                    map.put("key" + i, new Date());
                    break;
                case "string":
                    map.put("key" + i, randomString(RANDOM.nextInt(MAX_STR_LENGTH)));
                    break;
                default:
                    break;
            }
        }

        return map;
    }

    /**
     * 返回内容随机的10位Integer数组
     *
     * @return intArry
     */
    public static Integer[] randomIntArry() {
        Integer[] intArry = new Integer[DEFAULT_ARRY_SIZE];
        for (int i = 0; i < intArry.length; i++) {
            intArry[i] = RANDOM.nextInt(DEFAULT_ARRY_BOUND_NUM);
        }
        return intArry;
    }

    /**
     * 返回内容随机的10位String数组
     *
     * @return strArry
     */
    public static String[] randomStrArry() {
        String[] strArry = new String[DEFAULT_ARRY_SIZE];
        for (int i = 0; i < strArry.length; i++) {
            strArry[i] = randomString();
        }
        return strArry;
    }

    /**
     * 返回内容随机的10位JsonObject数组
     *
     * @return jsonObjects
     */
    public static JsonObject[] randomJsonObjects() {
        JsonObject[] jsonObjects = new JsonObject[DEFAULT_ARRY_SIZE];
        for (int i = 0; i < jsonObjects.length; i++) {
            jsonObjects[i] = new JsonObject();
        }
        return jsonObjects;
    }
}
