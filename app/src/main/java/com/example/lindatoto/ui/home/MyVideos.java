package com.example.lindatoto.ui.home;

public class MyVideos {
    private String description,thumb,title,videoid;

    public MyVideos(String title, String description, String thumb, String videoid) {
        this.title = title;
        this.description = description;
        this.thumb = thumb;
        this.videoid = videoid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public MyVideos(){

    }
}
