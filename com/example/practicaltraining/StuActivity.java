package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StuActivity extends AppCompatActivity {

    Spinner spinner;
    TextView time;
    Button btn_in;
    Button btn_lea;
    EditText reason;
    DBManager dbManager;
    MyDBHelper myDBHelper;
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);
        spinner=findViewById(R.id.spinner);
        time=findViewById(R.id.textView5);
        btn_in=findViewById(R.id.button5);
        btn_lea=findViewById(R.id.button4);
        reason=findViewById(R.id.editTextTextMultiLine);
        myDBHelper=new MyDBHelper(this);
        dbManager=new DBManager(this,myDBHelper);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        spinner_init();
        time.setText(getTime());
        //签到按钮点击事件
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Record record=new Record(spinner.getSelectedItem().toString(),sharedPreferences.getString("user_num","value"),getTime(),"签到");
                dbManager.addRecord(record);
            }
        });
        //请假按钮点击事件
        btn_lea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private String getTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String str = sdf.format(date);
        return str;
    }
    private void spinner_init(){
        List<String> data_list=new ArrayList<>();
        data_list=dbManager.selectChoCur(sharedPreferences.getString("user_num",""));
        arrayAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,data_list);
        spinner.setAdapter(arrayAdapter);
    }
}