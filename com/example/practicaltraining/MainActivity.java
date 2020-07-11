package com.example.practicaltraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper=new MyDBHelper(this,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText login_count=findViewById(R.id.editTextTextEmailAddress);
        EditText login_pwd=findViewById(R.id.editTextTextPassword);
        Button btn_login=findViewById(R.id.btn_login);
    }
}