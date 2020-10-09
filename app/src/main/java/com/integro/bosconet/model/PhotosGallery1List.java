package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotosGallery1List {

    @SerializedName("bosconet_photos")
    private ArrayList<PhotosGallery1> photosGallery1ArrayList;

    public ArrayList<PhotosGallery1> getPhotosGallery1ArrayList() {
        return photosGallery1ArrayList;
    }

    public void setPhotosGallery1ArrayList(ArrayList<PhotosGallery1> photosGallery1ArrayList) {
        this.photosGallery1ArrayList = photosGallery1ArrayList;
    }

    private String success;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }
}
