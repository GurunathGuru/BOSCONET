package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {

    @SerializedName("bosconet_news")
    private ArrayList<News> newsListArrayList;

    private String success;

    private String message;

    public ArrayList<News> getNewsListArrayList() {
        return newsListArrayList;
    }

    public void setNewsListArrayList(ArrayList<News> newsListArrayList) {
        this.newsListArrayList = newsListArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
