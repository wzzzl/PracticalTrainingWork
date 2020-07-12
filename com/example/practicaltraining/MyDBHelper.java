package com.example.practicaltraining;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

//    private static final String db_name="PracticalTrainingDB";
    private static final String CREATE_TABLE_ADM="create table administrator(adm_count String primary key,adm_pwd String)"; //管理员表
    private static final String CREATE_TABLE_STU=
            "create table student(stu_num String primary key,"+
                    "stu_name String,"+
                    "stu_pwd String"+
                    "stu_sex"+
                    "stu_contact"+
                    ")";  //学生表
    private static final String CREATE_TABLE_TEA=
            "create table teacher(tea_num String primary key,"+
                    "tea_name String,"+
                    "tea_pwd String,"+
                    "tea_contact String"+
                    ")";  //老师表
    private static final String CREATE_TABLE_LEA=
            "create table leave(lea_id integer primary key autoincrement,"+
                    "lea_stu_num String,"+
                    "lea_cla_num String,"+
                    "lea_time datetime,"+
                    "lea_reason String"+
                    ")";  //请假表
    private static final String CREATE_TABLE_ATTEN=
            "create table record(rec_num integer primary key autoincrement,"+
                    "rec_stu_num String,"+
                    "rec_ass_num String,"+
                    "rec_time datetime,"+
                    "rec_result String"+
                    ")";  //考勤记录表
//    private static final String CREATE_TABLE_ASSIGN=
//            "create table assign(ass_num int primary key autoincrement,"+
//                    "ass_tea_num String,"+
//                    "ass_position String,"+
//                    "ass_time String"+
//                    ")";  //授课表
    private static final String CREATE_TABLE_ASSIGN=
        "create table assign(ass_num integer primary key autoincrement,"+
                "ass_tea_num String,"+
                "ass_position String,"+
                "ass_time String"+
                ")";//授课表
    private static final String CREATE_TABLE_CLASS=
            "create table class(cla_num String primary key ,"+
                    "cla_name String"+
                    ")";  //课程表
    private static final String CREATE_TABLE_CHOOSE=
            "create table choose(cho_num integer primary key autoincrement,"+
                    "cho_stu_num String,"+
                    "cho_ass_num String"+
                    ")";  //选课表

    public MyDBHelper(Context content, String name,SQLiteDatabase.CursorFactory factory,int version){
        super(content,name,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {//在数据库中创建表
        db.execSQL(CREATE_TABLE_ADM);
        db.execSQL(CREATE_TABLE_ASSIGN);
        db.execSQL(CREATE_TABLE_ATTEN);
        db.execSQL(CREATE_TABLE_CHOOSE);
        db.execSQL(CREATE_TABLE_CLASS);
        db.execSQL(CREATE_TABLE_LEA);
        db.execSQL(CREATE_TABLE_STU);
        db.execSQL(CREATE_TABLE_TEA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
