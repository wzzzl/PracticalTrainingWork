package com.example.practicaltraining;

public class Record {
    private String ass_num;
    private String stu_num;
    private String time;
    private String result;

    public Record(String ass_num, String stu_num, String time, String result) {
        this.ass_num = ass_num;
        this.stu_num = stu_num;
        this.time = time;
        this.result = result;
    }

    public String getAss_num() {
        return ass_num;
    }

    public void setAss_num(String ass_num) {
        this.ass_num = ass_num;
    }

    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
