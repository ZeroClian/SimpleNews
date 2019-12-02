package com.example.simplenews;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class BrowseNewsActivity extends AppCompatActivity {

    private DrawerLayout browseDL;
    private WebView webView;
    private FloatingActionButton FAB;
    private int[] color = new int[]{R.color.colorPrimary,R.color.colorPrimaryDark};
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.browse_toolbar);
        setSupportActionBar(toolbar);
        browseDL = (DrawerLayout) findViewById(R.id.browse_drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        webView = (WebView)findViewById(R.id.webView);
        FAB = (FloatingActionButton) findViewById(R.id.float_collection);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num%2==1){
                    ColorStateList colorStateList = ContextCompat.getColorStateList(getApplicationContext(),R.color.colorPrimary);
                    FAB.setBackgroundTintList(colorStateList);
                    num++;
                    Toast.makeText(BrowseNewsActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                }else {
                    ColorStateList colorStateList = ContextCompat.getColorStateList(getApplicationContext(),R.color.colorAccent);
                    FAB.setBackgroundTintList(colorStateList);
                    num++;
                    Toast.makeText(BrowseNewsActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String pic_url = getIntent().getStringExtra("content_url");
        webView.loadUrl(pic_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
