package cn.my3gods.demotest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GuavaImmutableTest {

    private static ImmutableList<String> immutableList;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void before() {

        objectMapper = new ObjectMapper();

        ArrayList<String> strs = new ArrayList<>();
        strs.add("Tom");
        strs.add("Jack");
        strs.add("Charlie");
        strs.add("Cookie");
        immutableList = ImmutableList.copyOf(strs);
    }

    @Test
    public void testGuavaImmutable() {
        System.out.println(immutableList);
        immutableList.forEach(System.out::println);
    }

    @Test
    public void testImmutableList2Json() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(immutableList);
        System.err.println(json);
        // 直接序列化为ImmutableList会解析报错，可以先序列化为List，再转换为ImmutableList
//        List list = objectMapper.readValue(json, ImmutableList.class);
        List list = objectMapper.readValue(json, ArrayList.class);
        System.err.println(list);
    }

    @Test
    public void testCreateImmutableList() {
        ImmutableList<String> strings = ImmutableList.<String>builderWithExpectedSize(4)
            .add("a")
            .add("b")
            .add("c")
            .build();
        System.err.println(strings);
    }


}
