package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SuccessStoryList {

    @SerializedName("success_story")
    private ArrayList<SuccessStory> successStoryArrayList;

    private String success;

    private String message;

    public ArrayList<SuccessStory> getSuccessStoryArrayList() {
        return successStoryArrayList;
    }

    public void setSuccessStoryArrayList(ArrayList<SuccessStory> successStoryArrayList) {
        this.successStoryArrayList = successStoryArrayList;
    }

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
