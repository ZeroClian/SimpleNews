package com.example.simplenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.simplenews.gson.User;

import org.litepal.crud.DataSupport;

public class Motto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motto);
        final EditText motto = (EditText) findViewById(R.id.new_motto);
        motto.setText(getIntent().getStringExtra("motto"));
        final String alter_name = getIntent().getStringExtra("alter_name");

        //返回information
        Button motto_back = (Button) findViewById(R.id.motto_back);
        motto_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改个性签名
        Button alter_motto = (Button) findViewById(R.id.alter_motto);
        alter_motto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user_motto = new User();
                user_motto.setMotto(motto.getText().toString());
                user_motto.updateAll("name=?",alter_name);
                finish();
            }
        });
    }
}
