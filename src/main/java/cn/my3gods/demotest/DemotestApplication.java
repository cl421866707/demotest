package cn.my3gods.demotest;

import cn.my3gods.demotest.interceptor.BcHttpRequestInterceptor;
import java.nio.charset.Charset;
import java.util.Collections;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.my3gods.demotest.mapper")
@SpringBootApplication
public class DemotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemotestApplication.class, args);
    }

    @Bean
    public RestTemplate bcRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
            .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.setInterceptors(Collections.singletonList(new BcHttpRequestInterceptor()));

        return restTemplate;
    }
}
