package org.xyz.monitor.dc.test;


import org.xyz.monitor.dc.common.Constant;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.xyz.monitor.dc.source.KafkaSource;

import java.util.Objects;

/**
 * @author siyongshuai
 * @date 2019/12/5 14:38
 */
public class TestFlinkReadKafka {
    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getParallelism();
        String topic = Constant.TEST_TOPIC;
        FlinkKafkaConsumer010 consumer010 = KafkaSource.createTestKafkaSource(topic, "group_test1");
        DataStreamSource<String> streamSource = env.addSource(consumer010);
        System.out.println("并发度---" + streamSource.getParallelism());

        streamSource
                .filter(Objects::nonNull)
                .map(x -> DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd HH:mm:ss.SSS") + "---" + x + "##" + x.getBytes().length)
                .print();

        try {
            env.execute("flink_read_kafka");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
