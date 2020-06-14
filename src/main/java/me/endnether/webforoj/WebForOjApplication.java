package me.endnether.webforoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("me.endnether.webforoj.mapper")
public class WebForOjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebForOjApplication.class, args);
        //new Test().redisTest();
    }

}
