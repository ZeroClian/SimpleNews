package com.example.simplenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.simplenews.gson.Data;
import com.example.simplenews.gson.News;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Index extends AppCompatActivity {
    private ListView lvNews;
    //声明Adapter作为listview的填充
    private NewsAdapter adapter;
    private List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
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
//            System.out.println("标题："+title);
//            System.out.println("日期："+date);
//            System.out.println("作者："+author_name);
//            System.out.println("网址："+content_url);
//            System.out.println("图片："+pic_url);
            dataList.add(new Data(uniquekey, title, date, category, author_name, content_url, pic_url));
        }
        //更新Adapter(务必在主线程中更新UI!!!)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
