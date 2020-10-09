package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OurTeamList {

    private String success;

    @SerializedName("bosconet_team")
    private ArrayList<OurTeam> ourTeamArrayList;

    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<OurTeam> getOurTeamArrayList() {
        return ourTeamArrayList;
    }

    public void setOurTeamArrayList(ArrayList<OurTeam> ourTeamArrayList) {
        this.ourTeamArrayList = ourTeamArrayList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
