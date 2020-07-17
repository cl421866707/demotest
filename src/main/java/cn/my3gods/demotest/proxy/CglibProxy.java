package cn.my3gods.demotest.proxy;

import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <br>Cglib动态代理</br>
 * CGLib是针对类来实现代理的，他的原理是对指定的目标生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:34
 */
@Slf4j
@RequiredArgsConstructor
public class CglibProxy<T> implements MethodInterceptor {

    private final T target;

    /**
     * 增强实现
     *
     * @param object 代理对象
     * @param method 需要增强的方法
     * @param args 需要增强方法的参数
     * @param methodProxy 需要增强的方法的代理
     * @return 返回值
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("代理--方法执行前：参数：Method={},args={}", method.getName(), args);
        Object result = methodProxy.invoke(target, args);
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
