package com.example.simplenews.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplenews.R;
import com.example.simplenews.gson.Data;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    //声明一个上下文的对象（后续的getView()方法当中，会用到LayoutInflater加载XML布局）
    private Context context;
    private List<Data> dataList;

    //构造方法
    public NewsAdapter(Context context, List<Data> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    //控制该Adapter包含列表项的个数
    @Override
    public int getCount() {
        return dataList.size();
    }

    //决定第position处的列表项内容
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    //决定第position处的列表项ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //决定第position处的列表项组件
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvAuth = (TextView) convertView.findViewById(R.id.tvAuth);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
        Data data = dataList.get(position);
        tvTitle.setText(data.getTitle());
        tvAuth.setText(data.getAuthorName());
        tvTime.setText(data.getDate());
        String pic_url = data.getThumbnail_pic_s();
        setPicBitmap(ivPic, pic_url);
        return convertView;
    }

    public static void setPicBitmap(final ImageView ivPic, final String pic_url){
        //设置图片需要访问网络，因此不能在主线程中设置
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpURLConnection conn = (HttpURLConnection)new URL(pic_url).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivPic.setImageBitmap(bitmap);
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
