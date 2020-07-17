package cn.my3gods.demotest.proxy;

import cn.my3gods.demotest.proxy.impl.OriginServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * <br>代理模式测试</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:27
 */
public class ProxyTest {

    @Test
    public void staticProxyTest() {
        OriginService originService = new OriginServiceImpl();
        StaticProxy staticProxy = new StaticProxy(originService);
        staticProxy.login("user_1", "123456");
    }

    @Test
    public void jdkProxyTest() {
        OriginService originService = new OriginServiceImpl();
        JdkProxy<OriginService> jdkProxy = new JdkProxy<>(originService);
        OriginService proxy = jdkProxy.createProxy();
        proxy.login("user_1", "123456");
    }

    @Test
    public void cglibProxyTest() {
        OriginService originService = new OriginServiceImpl();
        CglibProxy<OriginService> cglibProxy = new CglibProxy<>(originService);
        OriginService proxy = cglibProxy.createProxy();
        proxy.login("user_1", "123456");
    }
}
