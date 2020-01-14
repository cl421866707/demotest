package cn.my3gods.demotest;

import cn.my3gods.demotest.model.User;
import org.junit.jupiter.api.Test;

public class GuavaObjectsTest {

    @Test
    public void testToString(){
        String charlie = User.builder().id("1").name("Charlie").build().toString();
        System.err.println(charlie);
    }

    @Test
    public void testCompareTo(){
        User tom = User.builder().id("500").name("Tom").age(36).build();
        User jack = User.builder().id("500").name("Tom").age(36).build();
//        System.out.println(tom.compareTo(null));
        System.err.println(tom.compareTo(jack)==0);
        System.err.println(tom.equals(jack));
    }
}
