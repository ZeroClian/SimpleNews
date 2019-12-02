package com.example.simplenews;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simplenews.gson.User;
import com.example.simplenews.util.MyDatabaseHelper;

public class Register extends AppCompatActivity {
    private EditText registerName;
    private EditText registerMail;
    private EditText registerPass;
    private MyDatabaseHelper dbHelperRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelperRegister = new MyDatabaseHelper(this,"user.db",null,1);
        Button register = (Button) findViewById(R.id.register_button);
        registerName = (EditText) findViewById(R.id.register_name);
        registerMail = (EditText) findViewById(R.id.register_mail);
        registerPass = (EditText) findViewById(R.id.register_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                //开始组装数据
                user.setName(registerName.getText().toString());
                user.setMail(registerMail.getText().toString());
                user.setPassword(registerPass.getText().toString());
                //保存数据
                user.save();
                Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
