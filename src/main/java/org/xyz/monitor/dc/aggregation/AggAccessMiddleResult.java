package org.xyz.monitor.dc.aggregation;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple7;
import org.xyz.monitor.bean.event.AccessLogEvent;
import org.xyz.monitor.bean.event.AccessMiddleResult;

/**
 * private String dt;
 * private String username;
 * private String executeSql;
 * private String path;
 * private String location;
 * private Long dataRows;
 * private Long cosTime;
 */


/**
 * @author siyongshuai
 * @time 2020-05-18 14:42
 */

public class AggAccessMiddleResult implements AggregateFunction<AccessLogEvent, Tuple7<String, String, String, String, String, Long, Long>, AccessMiddleResult> {
    @Override
    public Tuple7<String, String, String, String, String, Long, Long> createAccumulator() {
        Tuple7<String, String, String, String, String, Long, Long> tp7 = new Tuple7<String, String, String, String, String, Long, Long>();
        tp7.f0 = "";
        tp7.f1 = "";
        tp7.f2 = "";
        tp7.f3 = "";
        tp7.f4 = "";
        tp7.f5 = 0L;
        tp7.f6 = 0L;
        return tp7;
    }

    @Override
    public Tuple7<String, String, String, String, String, Long, Long> add(AccessLogEvent value, Tuple7<String, String, String, String, String, Long, Long> accumulator) {
        Tuple7<String, String, String, String, String, Long, Long> tp7 = new Tuple7<String, String, String, String, String, Long, Long>();
        tp7.f0 = value.getDt();
        tp7.f1 = value.getUsername();
        tp7.f2 = value.getExecuteSql();
        tp7.f3 = value.getPath();
        tp7.f4 = value.getLocation();
        tp7.f5 = Math.max(value.getDataRows(), accumulator.f5);
        tp7.f6 = Math.max(value.getCosTime(), accumulator.f6);


        return null;
    }

    @Override
    public AccessMiddleResult getResult(Tuple7<String, String, String, String, String, Long, Long> accumulator) {
        return null;
    }

    @Override
    public Tuple7<String, String, String, String, String, Long, Long> merge(Tuple7<String, String, String, String, String, Long, Long> a, Tuple7<String, String, String, String, String, Long, Long> b) {
        return null;
    }
}
