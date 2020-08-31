package com.example.lindatoto.models;

public class Patient {

       String name,email,age,location,educationstatus,workingstatus,profilepic,about,userId;

    public Patient(String name, String email, String age, String location, String educationstatus, String workingstatus, String profilepic, String about, String userId) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.location = location;
        this.educationstatus = educationstatus;
        this.workingstatus = workingstatus;
        this.profilepic = profilepic;
        this.about = about;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducationstatus() {
        return educationstatus;
    }

    public void setEducationstatus(String educationstatus) {
        this.educationstatus = educationstatus;
    }

    public String getWorkingstatus() {
        return workingstatus;
    }

    public void setWorkingstatus(String workingstatus) {
        this.workingstatus = workingstatus;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Patient(){

    }
}
