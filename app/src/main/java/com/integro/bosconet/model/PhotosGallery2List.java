package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotosGallery2List {

    private String success;

    @SerializedName("photoimages")
    private ArrayList<PhotosGallery2> photosGallery2ArrayList;

    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<PhotosGallery2> getPhotosGallery2ArrayList() {
        return photosGallery2ArrayList;
    }

    public void setPhotosGallery2ArrayList(ArrayList<PhotosGallery2> photosGallery2ArrayList) {
        this.photosGallery2ArrayList = photosGallery2ArrayList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
