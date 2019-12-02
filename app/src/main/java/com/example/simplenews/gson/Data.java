package com.example.simplenews.gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2019/11/25.
 */

public class Data extends DataSupport {

        @SerializedName("uniquekey")
        private String uniqueKey;

        private String title;

        private String date;

        private String category;

        @SerializedName("author_name")
        private String authorName;

        private String url;

        private String thumbnail_pic_s;

    public Data() {
    }

    public Data(String uniqueKey, String title, String date, String category, String authorName, String url, String thumbnail_pic_s){
            setUniqueKey(uniqueKey);
            setTitle(title);
            setDate(date);
            setCategory(category);
            setAuthorName(authorName);
            setUrl(url);
            setThumbnail_pic_s(thumbnail_pic_s);
        }

        public String getUniqueKey() {
            return uniqueKey;
        }

        public void setUniqueKey(String uniqueKey) {
            this.uniqueKey = uniqueKey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }
}
