package com.integro.bosconet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsletterList {

    @SerializedName("bosconet_newsletter")
    private List<Newsletter> arrNewsletterList;

    private String success;

    private String message;

    public List<Newsletter> getArrNewsletterList() {
        return arrNewsletterList;
    }

    public void setArrNewsletterList(List<Newsletter> arrNewsletterList) {
        this.arrNewsletterList = arrNewsletterList;
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
