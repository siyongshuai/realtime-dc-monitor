package org.xyz.monitor.dc.watermaker;

import org.xyz.monitor.rules.BasicEvent;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

/**
 * @author siyongshuai
 * @date 2019/12/3 17:07
 */
public class TenSecondsWaterMaker implements AssignerWithPeriodicWatermarks<BasicEvent> {
    long currentMaxTimestamp = 0L;
    long maxOutOfOrderness = 10000L;
    Watermark watermark = null;

    @Override
    public long extractTimestamp(BasicEvent element, long previousElementTimestamp) {
        long timeStamp = element.extractEventTimeMillis();
        currentMaxTimestamp = Math.max(timeStamp, currentMaxTimestamp);
        return timeStamp;
    }

    /**
     * currentMaxTimestamp - maxOutOfOrderness
     *
     * @return
     */
    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness);
        return watermark;
    }


}
