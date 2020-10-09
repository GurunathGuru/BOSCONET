package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryVideosList {

    @SerializedName("bosconet_videos")
    private ArrayList<GalleryVideos> galleryVideosArrayList;

    private String success;

    private String message;

    public ArrayList<GalleryVideos> getGalleryVideosArrayList() {
        return galleryVideosArrayList;
    }

    public void setGalleryVideosArrayList(ArrayList<GalleryVideos> galleryVideosArrayList) {
        this.galleryVideosArrayList = galleryVideosArrayList;
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
