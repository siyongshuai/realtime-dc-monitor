package org.xyz.monitor.dc.conf;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.IOException;

/**
 * @author siyongshuai
 * @date 2019/12/3 22:11
 */
public class FlinkConf {

    private static String product_mode = "product";
    private static String run_mode_param = "mode";
    private static String run_mode_default = product_mode;

    public static StreamExecutionEnvironment initEnvConf(StreamExecutionEnvironment env, ParameterTool params) throws IOException {
        if (!params.get(run_mode_param, run_mode_default).equalsIgnoreCase(product_mode)) {
            System.out.println("在调试模式下运行");
        } else {
            // 每隔1000 ms进行启动一个检查点【设置checkpoint的周期】
            env.enableCheckpointing(900000);
            // 高级选项：
            // 设置模式为exactly-once （这是默认值）
            env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
            // 检查点必须在一分钟内完成，或者被丢弃【checkpoint的超时时间】
            env.getCheckpointConfig().setCheckpointTimeout(600000);
            // 确保检查点之间有至少500 ms的间隔【checkpoint最小间隔】
            env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
            // 同一时间只允许进行一个检查点
            env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
            // 表示一旦Flink处理程序被cancel后，会保留Checkpoint数据，以便根据实际需要恢复到指定的Checkpoint【详细解释见备注】
            //ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION:表示一旦Flink处理程序被cancel后，会保留Checkpoint数据，以便根据实际需要恢复到指定的Checkpoint
            //ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION: 表示一旦Flink处理程序被cancel后，会删除Checkpoint数据，只有job执行失败的时候才会保存checkpoint
            env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
            String filebackend = "hdfs://xinmengdata-dsp/flink/checkpoints";
            env.setStateBackend(new RocksDBStateBackend(filebackend, true));
        }


        return env;
    }
}
