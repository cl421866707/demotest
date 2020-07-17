package cn.my3gods.demotest.proxy;

import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <br>Cglib动态代理</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:34
 */
@Slf4j
@RequiredArgsConstructor
public class CglibProxy<T> implements MethodInterceptor {

    private final T target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("代理--方法执行前：参数：Method={},args={}", method.getName(), objects);
        Object result = methodProxy.invoke(target, objects);
        log.info("代理--方法执行后：结果：result={}", result);
        return result;
    }

    public T createProxy() {
        //1.创建Enhancer
        Enhancer enhancer = new Enhancer();
        //2.传递目标对象的class
        enhancer.setSuperclass(target.getClass());
        //3.设置回调操作
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
}
