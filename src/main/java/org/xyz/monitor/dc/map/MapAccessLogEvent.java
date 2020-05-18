package org.xyz.monitor.dc.map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.xyz.monitor.bean.event.AccessLog;
import org.xyz.monitor.bean.event.AccessLogEvent;

import static org.xyz.monitor.dc.common.Constant.yyyyMMdd_PATTERN;

/**
 * @author siyongshuai
 * @time 2020-05-18 12:08
 */


public class MapAccessLogEvent implements MapFunction<String, AccessLogEvent> {

    @Override
    public AccessLogEvent map(String s) {

        AccessLog accessLog = AccessLog.extractEventFromString(s);
        if (accessLog != null) {
            AccessLogEvent accessLogEvent = new AccessLogEvent();
            accessLogEvent.setEventTimeMillis(accessLog.getDateline());
            accessLogEvent.setDt(DateFormatUtils.format(accessLog.getDateline(), yyyyMMdd_PATTERN));
            accessLogEvent.setDataRows(accessLog.getDataRows());
            accessLogEvent.setCosTime(accessLog.getCosTime());
            accessLogEvent.setLocation(accessLog.getLocation());
            return accessLogEvent;
        } else {
            return null;
        }
    }
}
