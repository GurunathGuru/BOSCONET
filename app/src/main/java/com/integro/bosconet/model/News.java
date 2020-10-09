package com.integro.bosconet.model;

import java.io.Serializable;

public class News implements Serializable {

    private String date;

    private String updated_at;

    private String s_img;

    private String description;

    private String id;

    private String title;

    private String l_img;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getS_img() {
        return s_img;
    }

    public void setS_img(String s_img) {
        this.s_img = s_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getL_img() {
        return l_img;
    }

    public void setL_img(String l_img) {
        this.l_img = l_img;
    }
}
