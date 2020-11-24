package com.coderead.boot.bean;
/**
 * @Copyright 源码阅读网 http://coderead.cn
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 鲁班大叔
 * @date 2020
 */
@Component("userInfo")
@PropertySource(value = {"classpath:/user.properties"}, encoding = "UTF-8")
@ConfigurationProperties(prefix = "admin")
@Validated
public class User {
    public int id;
    @NotNull
    public String name;
    public Sex sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date birthday;
    @NotEmpty
    public List<String> labels;
    public Map<String, Address> address;
    @Email
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<String, Address> getAddress() {
        return address;
    }

    public void setAddress(Map<String, Address> address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public enum Sex {
        man, woman
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", labels=" + labels +
                ", address=" + address +
                '}';
    }
}

