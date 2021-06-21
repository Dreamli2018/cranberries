package com.cranberries.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/06/21 10:05
 * @description ：es配置类
 */

@Configuration
@Slf4j
public class ElasticConfig {

    @Value("${spring.elasticsearch.host}")
    private String host;

    @Value("${spring.elasticsearch.port}")
    private Integer port;

    @Value("${spring.elasticsearch.cluster.name}")
    private String clusterName;

    private TransportClient transportClient;

    @Bean
    public TransportClient client() {
        Settings settings = Settings.EMPTY;

        if (ObjectUtils.isNotEmpty(settings)) {
            settings = Settings.builder().put("cluster.name", clusterName).build();
        }

        try {
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(host), port);
            transportClient = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
        } catch (Exception e) {
            log.error("创建es客户端失败！");
            e.printStackTrace();
        }
        log.info("创建es客户端成功！");
        return transportClient;
    }

    @Bean
    public BulkProcessor bulkProcessor() throws UnknownHostException {
        Settings settings = Settings.EMPTY;

        if (ObjectUtils.isNotEmpty(settings)) {
            settings = Settings.builder().put("cluster.name", clusterName).build();
        }

        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(host), port);
        transportClient = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);

        return BulkProcessor.builder(transportClient, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                log.error("{} data bulk failed,reason :{}", request.numberOfActions(), failure);
            }
        }).setBulkActions(1000) // 当请求数量达到1000条时，刷新一次批量请求。默认值为1000
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB)) // 当前操作达到5M时刷新一次批量请求
                .setFlushInterval(TimeValue.timeValueSeconds(5)) // 间隔多久刷新一次
                .setConcurrentRequests(1) // 设置并发请求的数量，默认为1
                // 设置批量请求失败时，批量处理器内部如何处理这些批量请求的重试，当前backoff策略，最初等待100ms,然后按照指数增长并重试3次
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
    }

    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
