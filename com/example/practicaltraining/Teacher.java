package com.example.practicaltraining;
public class Teacher {
    private String number;
    private String name;
    private String password;
    private String contact;

    public Teacher(String number, String name, String password, String contact) {
        this.number = number;
        this.name = name;
        this.password = password;
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
