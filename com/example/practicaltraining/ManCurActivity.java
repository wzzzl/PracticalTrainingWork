package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManCurActivity extends AppCompatActivity {

    EditText cur_num;
    EditText cur_name;
    Button addcur;
    DBManager dbManager;
    MyDBHelper myDBHelper;
    TextView nowcur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_cur);
        cur_num=findViewById(R.id.cur_num);
        cur_name=findViewById(R.id.cur_name);
        addcur=findViewById(R.id.btn_addcur);
        myDBHelper=new MyDBHelper(this);
        dbManager=new DBManager(this,myDBHelper);
        nowcur=findViewById(R.id.dbcontain);
        nowcur.setText(dbManager.showAll("curriculum"));
        addcur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=cur_num.getText().toString();
                String name=cur_name.getText().toString();
                if(num.equals("")||name.equals("")){
                    Toast.makeText(getApplicationContext(),"请正确填写课程信息！", Toast.LENGTH_SHORT).show();
                }else{
                    Curriculum curriculum=new Curriculum(num,name);
                    dbManager.addCurriculum(curriculum);
                }
            }
        });
    }
}