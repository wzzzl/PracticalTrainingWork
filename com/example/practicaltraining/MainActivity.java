package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper=new MyDBHelper(this,1);//创建数据库对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText login_count=findViewById(R.id.editTextTextEmailAddress);
        EditText login_pwd=findViewById(R.id.editTextTextPassword);
        Button login_btn=findViewById(R.id.btn_login);
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
        if(radioGroup.getCheckedRadioButtonId()==-1){//返回值为1则说明未选择单选按钮
            Toast.makeText(getApplicationContext(),"请选择登录身份！", Toast.LENGTH_SHORT).show();
        } else{
            String str_checked=btn_checked.getText().toString();
            if(str_checked.equals("Student")){
                Intent intent = new Intent(MainActivity.this, com.example.practicaltraining.StuActivity.class);
                startActivity(intent);//启动学生Activity
            }else if(str_checked.equals("Teacher")){
                Intent intent = new Intent(MainActivity.this, com.example.practicaltraining.TeaActivity.class);
                startActivity(intent);//启动老师Activity
            }else if(str_checked.equals("Administrator")){
                Intent intent = new Intent(MainActivity.this, com.example.practicaltraining.AdmActivity.class);
                startActivity(intent);//启动管理员Activity
            }
        }
    }
}