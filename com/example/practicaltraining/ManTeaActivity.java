package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManTeaActivity extends AppCompatActivity {

    EditText tea_num;
    EditText tea_name;
    EditText tea_pwd;
    EditText tea_con;
    Button add_tea;
    TextView now_tea;
    DBManager dbManager;
    MyDBHelper myDBHelper;
    EditText ass_cla_num;
    EditText ass_tea_num;
    EditText ass_time;
    EditText ass_pos;
    Button add_ass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tea);
        //初始化控件
        tea_num=findViewById(R.id.editTextNumber2);
        tea_name=findViewById(R.id.editTextTextPersonName2);
        tea_pwd=findViewById(R.id.editTextTextPassword2);
        tea_con=findViewById(R.id.editTextPhone2);
        add_tea=findViewById(R.id.button);
        now_tea=findViewById(R.id.textView);
        ass_cla_num=findViewById(R.id.textView2);
        ass_tea_num=findViewById(R.id.editTextNumber3);
        ass_time=findViewById(R.id.textView3);
        ass_pos=findViewById(R.id.textView4);
        add_ass=findViewById(R.id.button2);
        myDBHelper=new MyDBHelper(this);
        dbManager=new DBManager(this,myDBHelper);
        //展示数据库中现有老师账号
        now_tea.setText(dbManager.showAll("teacher"));
        //添加老师按钮点击事件
        add_tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_num=tea_num.getText().toString();
                String str_name=tea_name.getText().toString();
                String str_pwd=tea_pwd.getText().toString();
                String str_con=tea_con.getText().toString();
                //要求填写信息至少有一项为空则提醒用户填写完整
                if(str_num.equals("")||str_name.equals("")||str_pwd.equals("")||str_con.equals("")){
                    Toast.makeText(getApplicationContext(),"请填写完整教师信息！", Toast.LENGTH_SHORT).show();
                }else {
                    Teacher teacher=new Teacher(str_num,str_name,str_pwd,str_con);
                    dbManager.addTeacher(teacher);
                }
            }
        });
        //添加授课按钮点击事件
        add_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_ass_cla=ass_cla_num.getText().toString();
                String str_ass_tea=ass_tea_num.getText().toString();
                String str_ass_time=ass_time.getText().toString();
                String str_ass_pos=ass_pos.getText().toString();
                if(str_ass_cla.equals("")||str_ass_pos.equals("")||str_ass_tea.equals("")||str_ass_time.equals("")){
                    Toast.makeText(getApplicationContext(),"请填写完整授课信息！", Toast.LENGTH_SHORT).show();
                }else{
                    Assign assign=new Assign(str_ass_cla,str_ass_tea,str_ass_time,str_ass_pos);
                    dbManager.addAssign(assign);
                }
            }
        });
    }
}