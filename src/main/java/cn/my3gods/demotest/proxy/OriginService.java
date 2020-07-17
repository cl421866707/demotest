package cn.my3gods.demotest.proxy;

/**
 * <br>被代理类接口</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:13
 */
public interface OriginService {

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 登陆结果
     */
    String login(String username, String password);
}
