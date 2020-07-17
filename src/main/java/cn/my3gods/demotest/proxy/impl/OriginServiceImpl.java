package cn.my3gods.demotest.proxy.impl;

import cn.my3gods.demotest.proxy.OriginService;
import cn.my3gods.demotest.util.JacksonUtils;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

/**
 * <br>被代理类的实现类</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/7/17 10:15
 */
@Slf4j
public class OriginServiceImpl implements OriginService {

    @Override
    public String login(String username, String password) {
        log.info("OriginServiceImpl.login");
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder().put("用户名", username).put("密码", password).build();
        return JacksonUtils.obj2Json(map);
    }
}
