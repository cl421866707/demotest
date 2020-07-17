package cn.my3gods.demotest.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * <br>静态代理</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:11
 */
@Slf4j
public class StaticProxy implements OriginService {

    /**
     * 被代理目标类
     */
    private OriginService target;

    /**
     * 通过构造器注入被代理的类对象
     */
    public StaticProxy(OriginService target) {
        this.target = target;
    }

    @Override
    public String login(String username, String password) {
        log.info("代理--方法执行前：参数：username={},password={}", username, password);

        // 被代理类实际执行被代理的方法，此行程序执行前后可编写增强的代码
        String result = target.login(username, password);

        log.info("代理--方法执行后：结果：result={}", result);

        return result;
    }
}
