package com.coderead.boot.bean;
/**
 * @Copyright 源码阅读网 http://coderead.cn
 */

/**
 * @author 鲁班大叔
 * @date 2020
 */
public class Address {
    String name;
    String city;
    String detailed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", detailed='" + detailed + '\'' +
                '}';
    }
}
