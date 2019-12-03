package com.example.simplenews.gson;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2019/12/3.
 */

public class Colltects extends DataSupport {
    private String title;
    private String author_name;
    private String date;
    private String url;

    public Colltects() {
    }

    public Colltects(String title, String author_name, String date, String url) {
        this.title = title;
        this.author_name = author_name;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
