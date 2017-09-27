package com.google.firebase.codelab.friendlychat;

/**
 * Created by 태중 on 2017-06-17.
 */

public class Person {


    private String id;
    private String pass;
    private String name;
    private String repass;
    private String email;
    private String homeadd;
    private String homenum;
    private String phonenum;

    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRePass() {
        return repass;
    }
    public void setRePass(String repass) {
        this.repass = repass;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void sethomeAdd(String homeadd) {
        this.homeadd = homeadd;
    }
    public String gethomeAdd() {
        return homeadd;
    }

    public void sethomeNum(String homenum) {
        this.homenum = homenum;
    }
    public String gethomeNum() {
        return homenum;
    }

    public void setphoneNum(String phonenum) {
        this.phonenum = phonenum;
    }
    public String getphoneNum() {
        return phonenum;
    }
    @Override
    public String toString() {
        return "Person [DoorPassword=" + name + "]";
    }





}
