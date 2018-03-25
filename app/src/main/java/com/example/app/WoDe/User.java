package com.example.app.WoDe;


import java.util.Date;

/**
 * Created by 1 on 2017/12/17.
 */

public class User{
    private String username;
    private String name;
    private int phone;
    private String password;
    private int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username,String name,int phone, String password) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }
}
