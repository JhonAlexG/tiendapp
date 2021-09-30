package com.example.tiendaapp;

import java.io.Serializable;

public class User implements Serializable {
    private String uName;
    private String uEmail;
    private String uImage;
    private String uPassword;

    public User(String uName, String uEmail, String uImage, String uPassword) {
        this.uName = uName;
        this.uEmail = uEmail;
        this.uImage = uImage;
        this.uPassword = uPassword;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}
