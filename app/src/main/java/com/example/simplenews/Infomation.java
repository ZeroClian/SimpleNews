package com.example.simplenews;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Infomation extends AppCompatActivity {

    final String[] gender = new String[]{"男","女"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        //返回主界面
        Button back = (Button) findViewById(R.id.info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置性别
        final TextView tv_sex = (TextView) findViewById(R.id.info_gender);
        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Infomation.this).setTitle("请选择你的性别")
                        .setItems(gender, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_sex.setText(gender[which].toString());
                            }
                        }).show();
            }
        });

        //设置个性签名
        final TextView tv_motto = (TextView) findViewById(R.id.info_motto);
        tv_motto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent motto = new Intent(Infomation.this,Motto.class);
                motto.putExtra("motto",tv_motto.getText().toString());
                startActivity(motto);
            }
        });
    }
}
