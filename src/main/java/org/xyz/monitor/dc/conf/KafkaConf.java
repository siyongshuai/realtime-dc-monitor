package org.xyz.monitor.dc.conf;

import java.util.Properties;

/**
 * @author siyongshuai
 * @date 2019/12/3 15:27
 */
public class KafkaConf {
    public static Properties createKafkaProps(String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node03.example.com:9092,node04.example.com:9092,node05.example.com:9092,node14.example.com:9092" +
                ",node17.example.com:9092,node18.example.com:9092,node19.example.com:9092,node20.example.com:9092"
        );
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("auto.offset.reset", "latest");
//        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("group.id", groupId);
        return props;
    }


    public static Properties createTestKafkaProps(String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node03.example.com:9092,node04.example.com:9092,node05.example.com:9092,node14.example.com:9092" +
                ",node17.example.com:9092,node18.example.com:9092,node19.example.com:9092,node20.example.com:9092"
        );
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("auto.offset.reset", "latest");
//        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1);
        props.put("max.poll.interval.ms", 1000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("group.id", groupId);
        return props;
    }

}
