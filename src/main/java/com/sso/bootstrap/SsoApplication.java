package com.sso.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenhaowen
 * @Description:
 * @date 2020/8/12 4:16 PM
 */
@SpringBootApplication
@ComponentScan("com.sso")
@MapperScan("com.sso.dao")
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
