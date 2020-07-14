package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    DBManager dbManager;
    EditText login_count;
    EditText login_pwd;
    Button login_btn;
    RadioGroup radioGroup;
    Button btn_checked;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBHelper=new MyDBHelper(this);
        final DBManager dbManager=new DBManager(this,myDBHelper);
//        dbManager.addAdministrator("1","1");//insert adm account
        login_count=findViewById(R.id.editTextTextEmailAddress);
        login_pwd=findViewById(R.id.editTextTextPassword);
        login_btn=findViewById(R.id.btn_login);

        radioGroup=findViewById(R.id.radioGroup);
        //用sp文件保存用户登录信息
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        init();//初始化，自动填写上次登录账号

        login_btn.setOnClickListener(new View.OnClickListener() {//点击按钮根据单选框启动Activity
            @Override
            public void onClick(View view) {
                chooseActivity();
            }
        });
    }
    private void chooseActivity(){
        final DBManager dbManager=new DBManager(this,myDBHelper);
        if(radioGroup.getCheckedRadioButtonId()==-1){//返回值为-1则说明未选择单选按钮
            Toast.makeText(getApplicationContext(),"请选择登录身份！", Toast.LENGTH_SHORT).show();
        } else{
            btn_checked=findViewById(radioGroup.getCheckedRadioButtonId());
            String str_checked=btn_checked.getText().toString();
            switch (str_checked) {
                case "Student":
                    if (dbManager.checkActPwd("Student", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, StuActivity.class);
                        saveLastUser();
                        startActivity(intent);//启动学生Activity
                    }
                    break;
                case "Teacher":
                    if (dbManager.checkActPwd("Teacher", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, TeaActivity.class);
                        saveLastUser();
                        startActivity(intent);//启动老师Activity
                    }
                    break;
                case "Administrator":
                    if (dbManager.checkActPwd("Administrator", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, AdmActivity.class);
                        saveLastUser();
                        startActivity(intent);//启动管理员Activity
                    }
                    break;
            }
        }
    }
    //保存最近用户信息
    private void saveLastUser(){
        editor=sharedPreferences.edit();
        editor.putString("user_num",login_count.getText().toString());
        editor.putString("user_pwd",login_pwd.getText().toString());
        editor.putString("user_type",btn_checked.getText().toString());
        editor.apply();
    }
    //初始化自动填写最近用户信息
    private void init(){
        login_count.setText(sharedPreferences.getString("user_num","value"));
        login_pwd.setText(sharedPreferences.getString("user_pwd",""));
        try{
            String user_type=sharedPreferences.getString("user_type","");
            switch (user_type){
                case "Student":
                    radioGroup.check(R.id.login_stu);
                    break;
                case "Teacher":
                    radioGroup.check(R.id.login_tea);
                    break;
                case "Administrator":
                    radioGroup.check(R.id.login_adm);
                    break;
            }
        }catch (Exception e){
            throw e;
        }

    }
}