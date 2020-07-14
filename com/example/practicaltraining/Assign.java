package com.example.practicaltraining;

public class Assign {
    private String cla_num;
    private String tea_num;
    private String time;
    private String position;
    public Assign(String cla_num, String tea_num, String time, String position) {
        this.cla_num = cla_num;
        this.tea_num = tea_num;
        this.time = time;
        this.position = position;
    }

    public String getCla_num() {
        return cla_num;
    }

    public void setCla_num(String cla_num) {
        this.cla_num = cla_num;
    }

    public String getTea_num() {
        return tea_num;
    }

    public void setTea_num(String tea_num) {
        this.tea_num = tea_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
