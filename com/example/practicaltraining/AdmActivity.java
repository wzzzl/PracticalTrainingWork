package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmActivity extends AppCompatActivity {

    Button stu_btn;
    Button tea_btn;
    Button cur_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        stu_btn=findViewById(R.id.btn_stu);
        tea_btn=findViewById(R.id.btn_tea);
        cur_btn=findViewById(R.id.btn_cur);

        stu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmActivity.this, ManStuActivity.class);
                startActivity(intent);//启动学生账号管理Activity
            }
        });
        tea_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmActivity.this, ManTeaActivity.class);
                startActivity(intent);//启动老师账号管理Activity
            }
        });
        cur_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmActivity.this, ManCurActivity.class);
                startActivity(intent);//启动课程管理Activity
            }
        });
    }
}