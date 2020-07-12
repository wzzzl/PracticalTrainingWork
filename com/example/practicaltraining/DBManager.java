package com.example.practicaltraining;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBManager {

    private Context context;
    private static DBManager instance;
    // 操作表的对象，进行增删改查
    private SQLiteDatabase writableDatabase;

    DBManager(Context context,MyDBHelper myDBHelper) {
        this.context = context;
//        MyDBHelper myDBHelper = new MyDBHelper(context, 1);
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
        try{
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
            Toast.makeText(context.getApplicationContext(),"添加学生成功！", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context.getApplicationContext(),"添加学生失败！", Toast.LENGTH_SHORT).show();
        }
    }
    public void addTeacher(Teacher teacher){
        try{
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
            Toast.makeText(context.getApplicationContext(),"添加老师成功！", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加老师失败！", Toast.LENGTH_SHORT).show();
        }
    }
    public void addCurriculum(Curriculum curriculum){
        try{
            ContentValues contentValues=new ContentValues();
            String num=curriculum.getNumber();
            contentValues.put("cla_num",num);
            String name=curriculum.getName();
            contentValues.put("cla_name",name);
            writableDatabase.insert("class",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加课程成功！", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加课程失败！", Toast.LENGTH_SHORT).show();
        }

    }
    public void addAdministrator(String count,String pwd){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("adm_count",count);
            contentValues.put("adm_pwd",pwd);
            writableDatabase.insert("administrator",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加管理员成功！", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加管理员失败！", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkActPwd(String type,String account,String pwd){
        switch (type) {
            case "Student": {//在学生表中验证账号密码
                Cursor cursor = writableDatabase.query("student", null, "stu_num=" + account, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String db_pwd = cursor.getString(cursor.getColumnIndex("stu_pwd"));
                    if (db_pwd.equals(pwd)) {
                        cursor.close();
                        return true;
                    } else {
                        cursor.close();
                        Toast.makeText(context.getApplicationContext(), "密码不正确！", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(context.getApplicationContext(), "账号不存在！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    return false;
                }
            }
            case "Teacher": {
                Cursor cursor = writableDatabase.query("teacher", null, "tea_num=" + account, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String db_pwd = cursor.getString(cursor.getColumnIndex("tea_pwd"));
                    if (db_pwd.equals(pwd)) {
                        cursor.close();
                        return true;
                    } else {
                        cursor.close();
                        Toast.makeText(context.getApplicationContext(), "密码不正确", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(context.getApplicationContext(), "账号不存在！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            case "Administrator": {
                String selection="adm_count='"+account+"'";
                Cursor cursor = writableDatabase.query("administrator", null, selection, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String db_pwd = cursor.getString(cursor.getColumnIndex("adm_pwd"));
                    if (db_pwd.equals(pwd)) {
                        cursor.close();
                        return true;
                    } else {
                        cursor.close();
                        Toast.makeText(context.getApplicationContext(), "密码不正确", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(context.getApplicationContext(), "账号不存在！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            default:
                return false;
        }
    }

    public String selectAll(String table){
        Cursor cursor=writableDatabase.query("class",null,null,null,null,null,null,null);
        String dbcontent = "";
        while (cursor.moveToNext()){
//            String count=cursor.getString(cursor.getColumnIndex("count"));
//            String pwd=cursor.getString(cursor.getColumnIndex("pwd"));
//            result += String.format("count is %s, pwd is %s", count, pwd) + "\n";
            String num=cursor.getString(cursor.getColumnIndex("cla_num"));
            String name=cursor.getString(cursor.getColumnIndex("cla_name"));
            dbcontent+=String.format("number is %s, name is %s",num,name)+"\n";
        }
        cursor.close();
        return dbcontent;
    }
}