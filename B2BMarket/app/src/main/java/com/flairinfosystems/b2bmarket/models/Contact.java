package com.flairinfosystems.b2bmarket.models;

/**
 * Created by user on 06-02-2017.
 */

public class Contact {
    public int id;
    public String name,company,email,pass,confirmpass;
    public byte[] imageByte;

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] ImageByte) {
        this.imageByte = ImageByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



}
