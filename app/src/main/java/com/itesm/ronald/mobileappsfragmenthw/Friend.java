package com.itesm.ronald.mobileappsfragmenthw;

/**
 * Created by Ronald on 2/16/2015.
 */
public class Friend {
    private String name, hobby, age, phone, address;
    private Boolean visible;

    public Friend(String name, String hobby, String age, String phone, String address) {
        this.name = name;
        this.hobby = hobby;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.visible = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
