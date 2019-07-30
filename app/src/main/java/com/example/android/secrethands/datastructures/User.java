package com.example.android.secrethands.datastructures;

/**
 * Created by Aly on 7/29/2019.
 */

public class User {
    private int age;
    private String gender;
    private String username;
    private int type;              //1->patient   2->doctor
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User() {
    }

    public User(int age, String gender, String username, int type,String ID) {
        this.age = age;
        this.gender = gender;
        this.username = username;
        this.type = type;
        this .ID=ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public int getType() {
        return type;
    }

}
