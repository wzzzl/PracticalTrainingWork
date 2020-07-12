package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBHelper=new MyDBHelper(this,"PTDB",null,1);
        final DBManager dbManager=new DBManager(this,myDBHelper);
 //       dbManager.addAdministrator("1","1");//insert adm account
        login_count=findViewById(R.id.editTextTextEmailAddress);
        login_pwd=findViewById(R.id.editTextTextPassword);
        login_btn=findViewById(R.id.btn_login);
        login_btn.setOnClickListener(new View.OnClickListener() {//点击按钮根据单选框启动Activity
            @Override
            public void onClick(View view) {
                chooseActivity();
            }
        });
    }
    private void chooseActivity(){
        RadioGroup radioGroup=findViewById(R.id.radioGroup);
        Button btn_checked=findViewById(radioGroup.getCheckedRadioButtonId());
        final DBManager dbManager=new DBManager(this,myDBHelper);
        if(radioGroup.getCheckedRadioButtonId()==-1){//返回值为1则说明未选择单选按钮
            Toast.makeText(getApplicationContext(),"请选择登录身份！", Toast.LENGTH_SHORT).show();
        } else{
            String str_checked=btn_checked.getText().toString();
            switch (str_checked) {
                case "Student":
                    if (dbManager.checkActPwd("student", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, StuActivity.class);
                        startActivity(intent);//启动学生Activity
                    }
                    break;
                case "Teacher":
                    if (dbManager.checkActPwd("teacher", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, TeaActivity.class);
                        startActivity(intent);//启动老师Activity
                    }
                    break;
                case "Administrator":
                    String temp_account = login_count.getText().toString();
                    String temp_pwd = login_pwd.getText().toString();
                    if (dbManager.checkActPwd("Administrator", login_count.getText().toString(), login_pwd.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, AdmActivity.class);
                        startActivity(intent);//启动管理员Activity
                    }
                    break;
            }
        }
    }
}