package com.cranberries.elasticsearch;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/15 17:37
 * @description ：es启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cranberries.book")
public class OpenElasticsearchApplication {

    public static void main(String[] args) {
        // 将应用程序和elastic-apm连接在一起：Attaches the Elastic Apm agent to the current JVM.
        ElasticApmAttacher.attach();
        // 启动应用
        SpringApplication.run(OpenElasticsearchApplication.class, args);
    }

}
