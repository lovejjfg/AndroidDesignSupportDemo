package com.example.zhangjun166.demo;

/**
 * Created by ZHANGJUN166 on 2015-10-21.
 */
public class EventBusBean {
    private String action;
    public Object tag;
    public int arg1;

    private static EventBusBean instance;

    private EventBusBean(String action) {
        this.action = action;
    }

    public static EventBusBean getInstance(String action) {
        if (instance == null)
            instance = new EventBusBean(action);
        else
            instance.setAction(action);
        return instance;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
