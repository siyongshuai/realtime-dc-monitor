package org.xyz.monitor.dc.map;

import org.apache.flink.api.common.functions.MapFunction;
import org.xyz.monitor.dc.event.AccessLogEvent;

public class MapAccessLogEvent implements MapFunction<String, AccessLogEvent> {

    @Override
    public AccessLogEvent map(String s) throws Exception {

        return AccessLogEvent.extractEventFromString(s);
    }
}
