package org.xyz.monitor.dc.event;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.xyz.monitor.rules.BasicEvent;

import static org.xyz.monitor.dc.common.Constant.EVENT_SPLIT_CHAR;


/**
 * @author siyongshuai
 * @time 2020-05-15 15:35
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogEvent implements BasicEvent {
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
    private Long costime;

    @Override
    public Long extractEventTimeMillis() {
        return this.getDateline();
    }

    @Override
    public String toJsonString() {

        return JSON.toJSONString(this, true);
    }


    public static AccessLogEvent extractEventFromString(String str) {
        String[] arr = str.split(EVENT_SPLIT_CHAR, -1);
        if (arr.length == 13) {
            try {
                AccessLogEvent event = new AccessLogEvent(
                        Long.valueOf(arr[0]),
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4],
                        Long.valueOf(arr[5]),
                        arr[6],
                        arr[7],
                        arr[8],
                        arr[9],
                        arr[10],
                        arr[11],
                        Long.valueOf(arr[12])

                );
                return event;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        } else {
            return null;
        }

    }
}
