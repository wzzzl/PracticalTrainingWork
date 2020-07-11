package com.example.practicaltraining;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private Context context;
    private static DBManager instance;
    // 操作表的对象，进行增删改查
    private SQLiteDatabase writableDatabase;

    private DBManager(Context context,MyDBHelper myDBHelper) {
        this.context = context;
//        MyDBHelper dbHelper = new MyDBHelper(context, 1);
        writableDatabase = myDBHelper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context,MyDBHelper myDBHelper) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context,myDBHelper);
                }
            }
        }
        return instance;
    }
    public void addStudent(Student student){
        ContentValues contentValues = new ContentValues();
        String num=student.getNumber();
        contentValues.put("stu_num",num);
        String name=student.getName();
        contentValues.put("stu_name",name);
        String sex=student.getSex();
        contentValues.put("stu_sex",sex);
        String contact=student.getContact();
        contentValues.put("stu_contact",contact);
        String pwd=student.getPassword();
        contentValues.put("stu_pwd",pwd);
        writableDatabase.insert("student", null, contentValues);
    }
    public void addTeacher(Teacher teacher){
        ContentValues contentValues = new ContentValues();
        String num=teacher.getNumber();
        contentValues.put("tea_num",num);
        String name=teacher.getName();
        contentValues.put("tea_name",name);
        String contact=teacher.getContact();
        contentValues.put("tea_contact",contact);
        String pwd=teacher.getPassword();
        contentValues.put("tea_pwd",pwd);
        writableDatabase.insert("teacher", null, contentValues);
    }
    public void addCurriculum(Curriculum curriculum){
        ContentValues contentValues=new ContentValues();
        String num=curriculum.getNumber();
        contentValues.put("cla_num",num);
        String name=curriculum.getName();
        contentValues.put("cla_name",name);
        writableDatabase.insert("class",null,contentValues);
    }
    public void addAdministrator(String count,String pwd){
        ContentValues contentValues=new ContentValues();
        contentValues.put("adm_count",count);
        contentValues.put("adm_pwd",pwd);
        writableDatabase.insert("administrator",null,contentValues);
    }
}