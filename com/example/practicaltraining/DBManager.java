package com.example.practicaltraining;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    public boolean addStudent(Student student){
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
            return true;
        }catch(Exception e){
            Toast.makeText(context.getApplicationContext(),"添加学生失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean addTeacher(Teacher teacher){
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
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加老师失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean addCurriculum(Curriculum curriculum){
        try{
            ContentValues contentValues=new ContentValues();
            String num=curriculum.getNumber();
            contentValues.put("cla_num",num);
            String name=curriculum.getName();
            contentValues.put("cla_name",name);
            writableDatabase.insert("class",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加课程成功！", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加课程失败！", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    public boolean addAdministrator(String count,String pwd){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("adm_count",count);
            contentValues.put("adm_pwd",pwd);
            writableDatabase.insert("administrator",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加管理员成功！", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加管理员失败！", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    public boolean addAssign(Assign assign){
        try{
            ContentValues contentValues=new ContentValues();
            String cla_num=assign.getCla_num();
            contentValues.put("ass_cla_num",cla_num);
            String tea_num=assign.getTea_num();
            contentValues.put("ass_tea_num",tea_num);
            String time=assign.getTime();
            contentValues.put("ass_time",time);
            String position=assign.getPosition();
            contentValues.put("ass_position",position);
            writableDatabase.insert("assign",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加授课成功！", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加课程失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean addChoose(String stunum,String assnum){
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("cho_stu_num",stunum);
            contentValues.put("cho_ass_num",assnum);
            writableDatabase.insert("choose",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"添加选课成功！", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"添加课程失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkActPwd(String type,String account,String pwd){
        switch (type) {
            case "Student": {//在学生表中验证账号密码
                String selection="stu_num='"+account+"'";
                Cursor cursor = writableDatabase.query("student", null, selection, null, null, null, null, null);
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

    public String showAll(String table){
        if(table.equals("curriculum")){
            Cursor cursor=writableDatabase.query("class",null,null,null,null,null,null,null);
            String dbcontent = "";
            while (cursor.moveToNext()){
                String num=cursor.getString(cursor.getColumnIndex("cla_num"));
                String name=cursor.getString(cursor.getColumnIndex("cla_name"));
                dbcontent+=String.format("number is %s, name is %s",num,name)+"\n";
            }
            cursor.close();
            return dbcontent;
        }else if(table.equals("student")){
            Cursor cursor=writableDatabase.query("student",null,null,null,null,null,null,null);
            String dbcontent = "";
            while (cursor.moveToNext()){
                String num=cursor.getString(cursor.getColumnIndex("stu_num"));
                String name=cursor.getString(cursor.getColumnIndex("stu_name"));
                String sex=cursor.getString(cursor.getColumnIndex("stu_sex"));
                String contact=cursor.getString(cursor.getColumnIndex("stu_contact"));
                dbcontent+=String.format("number is %s, name is %s, sex is %s, contact is %s",num,name,sex,contact)+"\n";
            }
            cursor.close();
            return dbcontent;
        }else{
            Cursor cursor=writableDatabase.query("teacher",null,null,null,null,null,null,null);
            String dbcontent = "";
            while (cursor.moveToNext()){
                String num=cursor.getString(cursor.getColumnIndex("tea_num"));
                String name=cursor.getString(cursor.getColumnIndex("tea_name"));
                String contact=cursor.getString(cursor.getColumnIndex("tea_contact"));
                dbcontent+=String.format("number is %s, name is %s, contact is %s",num,name,contact)+"\n";
            }
            cursor.close();
            return dbcontent;
        }
    }//读取table中所有数据

    public ArrayList selectChoCur(String stu_num){
        //根据学号在选课表找出选课编号，再根据选课编号找出课程编号，最后通过课程编号得到课程名称
        ArrayList cur_num_list=new ArrayList();
        ArrayList ass_num_list =new ArrayList();
        ArrayList cur_name_list=new ArrayList();
        String ass_num;
        String cur_num;
        Cursor cursor=writableDatabase.query(true,"choose",null,"cho_stu_num='"+stu_num+"'",null,null,null,null,null);
        try{//从choose找出学号对应授课号
            while(cursor.moveToNext()){
                ass_num_list.add(cursor.getString(cursor.getColumnIndex("cho_ass_num")).toString());
            }
            //每一个授课号都到授课表中找到对应的课程号
            for(int i=0;i<ass_num_list.size();i++){
                ass_num= (String) ass_num_list.get(i);
                cursor=writableDatabase.query(true,"assign",null,"ass_num='"+ass_num+"'",null,null,null,null,null);
                while (cursor.moveToNext()){
                    cur_num_list.add(cursor.getString(cursor.getColumnIndex("ass_cla_num")));
                }
            }
            //根据课程号在课程表中找到对应课程名称
            for(int i=0;i<cur_num_list.size();i++){
                cur_num= (String) cur_num_list.get(i);
                cursor=writableDatabase.query(true,"class",null,"cla_num='"+cur_num+"'",null,null,null,null,null);
                cursor.moveToFirst();
                cur_name_list.add(cursor.getString(cursor.getColumnIndex("cla_name")));
            }
            cursor.close();
            return cur_name_list;
        }catch (Exception e){
            cursor.close();
            Toast.makeText(context.getApplicationContext(), "未选课！", Toast.LENGTH_SHORT).show();
            return cur_name_list;
        }

    }

    public boolean addRecord(Record record){
        try{
            ContentValues contentValues=new ContentValues();
            String ass_num=record.getAss_num();
            contentValues.put("rec_ass_num",ass_num);
            String stu_num=record.getStu_num();
            contentValues.put("rec_stu_num",stu_num);
            String time=record.getTime();
            contentValues.put("rec_time",time);
            String result=record.getResult();
            contentValues.put("rec_result",result);
            writableDatabase.insert("record",null,contentValues);
            Toast.makeText(context.getApplicationContext(),"签到成功！", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),"签到失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}