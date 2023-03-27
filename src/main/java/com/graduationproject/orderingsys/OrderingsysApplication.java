package com.graduationproject.orderingsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @BelongsProject: orderingsys
 * @BelongsPackage: com.graduationproject.orderingsys
 * @Author: Dongrun Li
 * @Date: 2023/3/20 23:47
 * @Description:
 */

@MapperScan("com.graduationproject.orderingsys")
@SpringBootApplication
public class OrderingsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderingsysApplication.class, args);
    }

}
