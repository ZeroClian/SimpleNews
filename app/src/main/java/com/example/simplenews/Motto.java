package com.example.simplenews;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.simplenews.gson.User;

import org.litepal.crud.DataSupport;

public class Motto extends AppCompatActivity {
    private DrawerLayout mottoDL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.motto_toolbar);
        setSupportActionBar(toolbar);
        mottoDL = (DrawerLayout) findViewById(R.id.motto_drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final EditText motto = (EditText) findViewById(R.id.new_motto);
        motto.setText(getIntent().getStringExtra("motto"));
        final String alter_name = getIntent().getStringExtra("alter_name");


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

    //返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
