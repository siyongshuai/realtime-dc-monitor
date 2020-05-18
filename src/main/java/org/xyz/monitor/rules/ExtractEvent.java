package org.xyz.monitor.rules;
/**
 *@author siyongshuai
 *@time 2020-05-15 15:33
 */

public interface ExtractEvent {

    /**
     * 从字符串中提取事件
     *
     * @param str
     * @return
     */
    Object extractEventFromString(String str);
}
