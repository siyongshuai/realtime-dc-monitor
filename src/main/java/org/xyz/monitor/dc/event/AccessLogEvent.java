package org.xyz.monitor.dc.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.xyz.monitor.rules.BasicEvent;
import org.xyz.monitor.rules.ExtractEvent;

import static org.xyz.monitor.dc.common.Constant.EVENT_SPLIT_CHAR;


/**
 * @author siyongshuai
 * @time 2020-05-15 15:35
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogEvent implements BasicEvent, ExtractEvent {
    private Long dateline;
    private String datetime;
    private String ip;
    private String username;
    private String fullName;
    private Long dataRows;
    private String executeSql;
    private String path;
    private String location;
    private String permissionStatus;
    private String callbackParam;
    private String json;
    private Long bigint;

    @Override
    public Long extractEventTimeMillis() {
        return this.getDateline();
    }

    @Override
    public Object extractEventFromString(String str) {
        String[] arr = str.split(EVENT_SPLIT_CHAR, -1);
        if (arr.length == this.getClass().getDeclaredFields().length) {
            AccessLogEvent event = new AccessLogEvent(

            );

            return event;
        } else {
            return null;
        }

    }
}
