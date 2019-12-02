package com.example.simplenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Motto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motto);
        EditText motto = (EditText) findViewById(R.id.new_motto);
        motto.setText(getIntent().getStringExtra("motto"));
    }
}
