package com.kenny.service.logistics.model.po.count;

/**
 * Created by Kenny on 2017/8/7.
 */
public class CountMap {
    String key;
    Object value;

    public CountMap(){}

    public CountMap(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
