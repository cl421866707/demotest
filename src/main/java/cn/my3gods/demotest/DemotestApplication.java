package cn.my3gods.demotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.my3gods.demotest.mapper")
@SpringBootApplication
public class DemotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemotestApplication.class, args);
    }
}
