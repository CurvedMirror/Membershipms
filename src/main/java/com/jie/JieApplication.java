package com.jie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jie.mapper")
@SpringBootApplication
public class JieApplication {

    public static void main(String[] args) {
        SpringApplication.run(JieApplication.class, args);
    }

}
