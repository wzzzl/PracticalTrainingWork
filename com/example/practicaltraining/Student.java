package com.example.practicaltraining;

import android.database.Cursor;

public class Student {
    private String number;
    private String name;
    private String sex;
    private String password;
    private String contact;

    public Student(String number, String name, String sex, String password, String contact) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
