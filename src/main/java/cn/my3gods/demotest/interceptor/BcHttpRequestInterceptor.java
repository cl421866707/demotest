package cn.my3gods.demotest.interceptor;

import cn.my3gods.demotest.enums.BcReqHeaderEnum;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * <br>设置通用请求头</br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 14:40
 */
public class BcHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(HttpHeaders.ACCEPT, "accept");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 设置BigCommerce的请求头
        Arrays.stream(BcReqHeaderEnum.values()).forEach(bcReqHeaderEnum -> headers.add(bcReqHeaderEnum.getName(), bcReqHeaderEnum.getValue()));
        return execution.execute(request, body);
    }
}
