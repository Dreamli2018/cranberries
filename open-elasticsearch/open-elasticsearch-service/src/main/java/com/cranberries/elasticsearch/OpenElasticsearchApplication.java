package com.cranberries.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/15 17:37
 * @description ：es启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OpenElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenElasticsearchApplication.class, args);
    }

}
