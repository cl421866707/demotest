package cn.my3gods.demotest.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanUtils {

    private BeanUtils() {
    }

    /**
     * 拷贝对象属性到指定类，并返回类对象
     *
     * @param object 拷贝对象
     * @param targetClass 目标类
     * @param <T> 目标类的类型
     * @return 目标类对象
     */
    public static <T> T copyProperties2Bean(Object object, Class<T> targetClass) {
        try {
            T targetObject = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(object, targetObject);
            return targetObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            log.error("BeanUtils.copyProperties2Bean Exception:{}", e);
        }
        return null;
    }
}
