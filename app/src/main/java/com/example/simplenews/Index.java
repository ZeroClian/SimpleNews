package com.example.simplenews;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.simplenews.gson.Data;
import com.example.simplenews.gson.News;
import com.example.simplenews.util.NewsAdapter;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Index extends AppCompatActivity {
    private ListView lvNews;
    private NewsAdapter adapter;
    private List<Data> dataList;
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;

    public TextView tvName;

    //接收传递过来的用户名
    private String main_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //接收
        main_name = getIntent().getStringExtra("username");
        //同步用户名
        tvName = (TextView) findViewById(R.id.nav_header_name);
        tvName.setText(new String(String.format(getResources().getString(R.string.nav_header_name),main_name)));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.daohang);
        }

        //侧滑栏内事件点击
        final NavigationView navView =(NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_myinfo:
                        mDrawerLayout.closeDrawers();
                        Intent info = new Intent(Index.this,Infomation.class);
                        info.putExtra("info_user",main_name);
                        startActivity(info);
                        break;
                    case R.id.nav_mycollection:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_news:
                        new AlertDialog.Builder(Index.this).setTitle("通知").setMessage("该功能暂未实现!")
                                .setPositiveButton("确定",null).show();
                        break;
                    case R.id.nav_back:
                        new AlertDialog.Builder(Index.this).setTitle("提示").setMessage("确定退出应用吗？")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).setNeutralButton("取消",null).show();
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });

        //新闻列表的获取
        lvNews = (ListView)findViewById(R.id.lvNews);
        dataList = new ArrayList<Data>();
        adapter = new NewsAdapter(this, dataList);
        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Data data = dataList.get(position);
                Intent intent = new Intent(Index.this, BrowseNewsActivity.class);
                intent.putExtra("content_url", data.getUrl());
                startActivity(intent);
            }
        });
        sendRequestWithOKHttp();

        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendRequestWithOKHttp();
            }
        });
    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://v.juhe.cn/toutiao/index?type=top&key=468a538795ca302846f994e7559df8a7")
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("测试：", responseData);
                    parseJsonWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //更新Adapter(务必在主线程中更新UI!!!)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void parseJsonWithGson(String jsonData){
        Gson gson = new Gson();
        News news = gson.fromJson(jsonData, News.class);
        List<Data> list = news.getResult().getData();
        for (int i=0; i<list.size(); i++){
            String uniquekey = list.get(i).getUniqueKey();
            String title = list.get(i).getTitle();
            String date = list.get(i).getDate();
            String category = list.get(i).getCategory();
            String author_name = list.get(i).getAuthorName();
            String content_url = list.get(i).getUrl();
            String pic_url = list.get(i).getThumbnail_pic_s();
//            System.out.println("日期："+date);
//            System.out.println("作者："+author_name);
//            System.out.println("标题："+title);
//            System.out.println("网址："+content_url);
//            System.out.println("图片："+pic_url);
            dataList.add(new Data(uniquekey, title, date, category, author_name, content_url, pic_url));
        }
    }

    //加载菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.setting:
                Toast.makeText(this, "You clicked Setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
