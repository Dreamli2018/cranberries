package com.cranberries.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = "com.cranberries.data.mapper")
@EnableFeignClients(basePackages = "com.cranberries.userapi.*")
public class DataCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCenterApplication.class, args);
    }

}
