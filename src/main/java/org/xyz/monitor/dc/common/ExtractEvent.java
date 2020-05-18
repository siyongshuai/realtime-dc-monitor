package org.xyz.monitor.dc.common;

import com.alibaba.fastjson.JSONObject;

import static org.xyz.monitor.dc.common.Constant.EVENT_SPLIT_CHAR;

public class ExtractEvent {
    public static JSONObject extractFromString(String str) {

        String[] arr = str.split(EVENT_SPLIT_CHAR, -1);

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < arr.length; i++) {

            jsonObject.put("col" + i, arr[i]);
        }

        return jsonObject;

    }
}

