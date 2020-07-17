package cn.my3gods.demotest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <br>JDK动态代理</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:33
 */
@Slf4j
@RequiredArgsConstructor
public class JdkProxy<T> implements InvocationHandler {

    private final T target;

    /**
     * 动态创建的代理类对象的增强方法
     *
     * @param proxy 代理对象
     * @param method 增强的方法
     * @param args 方法中的参数
     * @return 返回值
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("代理--方法执行前：参数：Method={},args={}", method.getName(), args);

        // 注意：此处不能使用proxy对象，而要使用原被代理对象，否则会导致OOM异常
        // 被代理类实际执行被代理的方法，此行程序执行前后可编写增强的代码
        Object result = method.invoke(target, args);

        log.info("代理--方法执行后：结果：result={}", result);
        return result;
    }

    /**
     * 动态创建并返回代理对象
     *
     * @return 代理后的增强对象
     */
    public T createProxy() {

        // 类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 实现的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 生成代理类对象，需要三个参数：被代理类的类加载器，实现的接口，实现InvocationHandler接口增强方法的增强类对象
        return (T) Proxy.newProxyInstance(classLoader, interfaces, this);
    }
}
