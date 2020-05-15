package org.xyz.monitor.dc.test;

import org.xyz.monitor.dc.event.AccessLogEvent;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(AccessLogEvent.class.getDeclaredFields().length);
    }
}
