package org.xyz.monitor.dc.source;

import org.xyz.monitor.dc.conf.KafkaConf;
import org.apache.flink.api.common.io.ratelimiting.GuavaFlinkConnectorRateLimiter;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

/**
 * @author siyongshuai
 * @date 2019/12/3 15:45
 */
public class KafkaSource {
    public static FlinkKafkaConsumer010 createKafkaSource(String topic, String groupId) {
        FlinkKafkaConsumer010 consumer = new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(), KafkaConf.createKafkaProps(groupId));
        consumer.setStartFromLatest();
        return consumer;
    }

    public static FlinkKafkaConsumer010 createTestKafkaSource(String topic, String groupId) {
        FlinkKafkaConsumer010 consumer = new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(),
                KafkaConf.createTestKafkaProps(groupId));
        GuavaFlinkConnectorRateLimiter rateLimiter = new GuavaFlinkConnectorRateLimiter();
//
        rateLimiter.setRate(3000);
//        rateLimiter.acquire(1);
        consumer.setRateLimiter(rateLimiter);
        consumer.setStartFromEarliest();
        return consumer;

    }
}
