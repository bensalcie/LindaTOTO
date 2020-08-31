package com.example.lindatoto;

/**
 *
 */
public class Users {
    private String Id;
    private String name;
    private  String about;
    private String age;
    private String workingStatus;
    private String school_status;
    private  String location;
    private String imageUrl;

    public Users(String name,String about, String age,String workingStatus,String school_status,String location,String imageUrl){
        this.name=name;
        this.about=about;
        this.age=age;
        this.workingStatus=workingStatus;
        this.school_status=school_status;
        this.location=location;
        this.imageUrl=imageUrl;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public String getSchool_status() {
        return school_status;
    }

    public void setSchool_status(String school_status) {
        this.school_status = school_status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
