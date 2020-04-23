package cn.my3gods.demotest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GuavaCollectionTest {

    /**
     * 直接初始化有值的List
     */
    @Test
    public void testLists() {
        // 使用一个经验公式：5L + arraySize + (arraySize / 10)计算创建的List的初始大小
        List<String> strList = Lists.newArrayList("1", "2", "3", "3", "3", "3", "3", "3");

        List<String> list1 = ImmutableList.of("1", "2", "3");
        List<Object> list2 = ImmutableList.builderWithExpectedSize(4).add("1").add("2").add("3").add("4").build();

        System.err.println(strList);

    }

    /**
     * 直接初始化有值的Map
     */
    @Test
    public void testMaps() {
        Map<String, String> map1 = ImmutableMap.of("1", "a", "2", "b", "3", "c", "4", "d", "5", "e");
        Map<Object, Object> map2 = ImmutableMap.builderWithExpectedSize(8).put("1", "a").put("2", "b").put("3", "c").put("4", "d").put("5", "e").build();

        System.err.println(map1);
        System.err.println(map2);

    }
}
