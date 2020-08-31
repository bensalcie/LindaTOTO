package com.example.lindatoto.ui.notifications;

import java.util.Calendar;

public class MyNotification {
     String notid,category,userId,message,date,postedon;

    public MyNotification(String notid, String category, String userId, String message, String date, String postedon) {
        this.notid = notid;
        this.category = category;
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.postedon = postedon;
    }

    public String getNotid() {
        return notid;
    }

    public void setNotid(String notid) {
        this.notid = notid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostedon() {
        return postedon;
    }

    public void setPostedon(String postedon) {
        this.postedon = postedon;
    }

    public MyNotification(){

     }
}
