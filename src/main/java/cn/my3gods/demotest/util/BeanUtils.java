package cn.my3gods.demotest.util;

import cn.my3gods.demotest.entity.KeyValuePair;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

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

    /**
     * 遍历获取对象的属性名-属性值
     *
     * @param targetObj 目标对象
     * @return 包含所有非空属性值的：（属性名-属性值）对象的集合
     */
    private static List<KeyValuePair> getFieldAndValue(Object targetObj) {
        if (null == targetObj) {
            return new ArrayList<>(0);
        }
        try {
            return getFieldAndValue(targetObj, targetObj.getClass(), 0);
        } catch (IllegalAccessException e) {
            log.error("SendBigCommerce - getFieldAndValue Exception:{}\n{}", e.getMessage(), e);
        }
        return new ArrayList<>(0);
    }

    /**
     * 遍历获取对象的属性名-属性值（结果集合避免扩容开销
     *
     * @param targetObj 目标对象
     * @param targetObjClass 目标Class
     * @param initialCapacity 子类属性数量
     * @return 包含所有非空属性值的：（属性名-属性值）对象的集合
     * @throws IllegalAccessException IllegalAccessException
     */
    private static List<KeyValuePair> getFieldAndValue(Object targetObj, Class targetObjClass, int initialCapacity) throws IllegalAccessException {
        // 声明结果集合
        List<KeyValuePair> result = null;
        // 获取当前对象声明的属性数组
        Field[] fields = targetObjClass.getDeclaredFields();
        // 结果集合的初始化大小：如果当前类为父类，则initialCapacity为子类的属性数量，此处为子类属性数量+当前类属性数量
        Integer initialCapacitySize = fields.length + initialCapacity;
        // 存在父类则对父类递归获取 属性-值 集合
        if (Objects.nonNull(targetObjClass.getSuperclass())) {
            result = getFieldAndValue(targetObj, targetObjClass.getSuperclass(), initialCapacitySize);
        }
        // 如无父类结果集合则初始化结果集合
        if (CollectionUtils.isEmpty(result)) {
            result = new ArrayList<>(initialCapacitySize);
        }
        // 遍历属性
        for (Field field : fields) {
            // 设置private可访问
            field.setAccessible(true);
            // 获取属性名与值，加入结果集合
            if (Objects.nonNull(field.get(targetObj))) {
                result.add(new KeyValuePair(field.getName(), field.get(targetObj).toString()));
            }
        }
        return result;
    }
}
