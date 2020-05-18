package org.xyz.monitor.bean.event;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.xyz.monitor.rules.BasicEvent;


/**
 * @author siyongshuai
 * @time 2020-05-18 14:27
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogEvent implements BasicEvent {

    private Long eventTimeMillis;
    private String dt;
    private String username;
    private Long dataRows;
    private String executeSql;
    private String path;
    private String location;
    private Long cosTime;

    @Override
    public Long extractEventTimeMillis() {
        return this.getEventTimeMillis();
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
