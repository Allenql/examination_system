package com.examination;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.examination.dao"})
public class ExaminationSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExaminationSysApplication.class, args);
    }

}
