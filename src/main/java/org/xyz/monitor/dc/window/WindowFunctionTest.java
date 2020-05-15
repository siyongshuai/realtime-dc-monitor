package org.xyz.monitor.dc.window;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author siyongshuai
 * @date 2019/12/4 18:02
 */
public class WindowFunctionTest implements WindowFunction<Tuple2<String, Long>, Tuple6<String, Integer, String, String, String, String>, String, TimeWindow> {

    @Override
    public void apply(String s, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<Tuple6<String, Integer, String, String, String, String>> out) throws Exception {
        ArrayList<Tuple2<String, Long>> arr = new ArrayList<>();
        input.forEach(e -> {
            arr.add(e);
        });
        Arrays.sort(arr.toArray());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Tuple6<String, Integer, String, String, String, String> tp6 = new Tuple6(s, arr.size(), format.format(arr.get(0).f1), format.format(arr.get(arr.size() - 1).f1), format.format(new Date(window.getStart())), format.format(new Date(window.getEnd())));

        out.collect(tp6);

    }
}
