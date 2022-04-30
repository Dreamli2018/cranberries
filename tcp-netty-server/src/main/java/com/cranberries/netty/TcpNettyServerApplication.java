package com.cranberries.netty;

import co.elastic.apm.attach.ElasticApmAttacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.net.InetAddress;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/10/14 16:02
 * @description ：
 */

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class TcpNettyServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        // 将应用程序和elastic-apm连接在一起：Attaches the Elastic Apm agent to the current JVM.
        ElasticApmAttacher.attach();
        SpringApplication.run(TcpNettyServerApplication.class, args);
    }

//    @Autowired
//    private TcpNettyServer tcpNettyServer;

    @Override
    public void run(String... args) throws Exception {
        log.info(InetAddress.getLocalHost().getHostAddress());
//        tcpNettyServer.start(new InetSocketAddress(8010));
    }
}
