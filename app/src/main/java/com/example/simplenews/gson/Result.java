package com.example.simplenews.gson;

import java.util.List;

/**
 * Created by Administrator on 2019/11/25.
 */

public class Result {

    private String stat;

    List<Data> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

