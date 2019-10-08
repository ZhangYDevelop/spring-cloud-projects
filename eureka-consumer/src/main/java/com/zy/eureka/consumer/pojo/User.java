package com.zy.eureka.consumer.pojo;

import java.io.Serializable;

/**
 * @AUTHOR zhangy
 * 2019-09-28  23:08
 */
public class User implements Serializable {

    private  String name ;

    private  String address;

    private  String tel;

    private int age;

    private  String id;



    public User(String name, String address, String tel, int age, String id) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.age = age;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", age=" + age +
                '}';
    }
}
