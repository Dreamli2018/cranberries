package com.cranberries.netty.client;

import co.elastic.apm.attach.ElasticApmAttacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/10/15 11:27
 * @description ：
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableScheduling
public class TcpNettyClientApplication {

    public static void main(String[] args) {
        // 将应用程序和elastic-apm连接在一起：Attaches the Elastic Apm agent to the current JVM.
        ElasticApmAttacher.attach();
        SpringApplication.run(TcpNettyClientApplication.class, args);
    }

}
