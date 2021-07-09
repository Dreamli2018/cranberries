package com.cranberries.book;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BookApplication {

    public static void main(String[] args) {
        // 将应用程序和elastic-apm连接在一起：Attaches the Elastic Apm agent to the current JVM.
        ElasticApmAttacher.attach();
        // 启动应用
        SpringApplication.run(BookApplication.class, args);
    }

}
