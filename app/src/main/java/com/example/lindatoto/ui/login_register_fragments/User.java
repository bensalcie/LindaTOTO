package com.example.lindatoto.ui.login_register_fragments;

public class User {
    private String useremail;
    private String password;
    public User() {
        //Empty Constructor For Firebase
    }
    public User(String useremail, String password)
    {
        this.useremail = useremail; //Parameterized for Program-Inhouse objects.
        this.password = password;
    }
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
