package org.xyz.monitor.dc.test;

import org.xyz.monitor.bean.event.AccessLog;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(AccessLog.class.getDeclaredFields().length);
        for (int i = 0; i < 13; i++) {
            System.out.println("arr[" + i + "],");
        }
    }
}
