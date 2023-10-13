package com.example.realfirestore;

public class ModelClass {
    String  name,email,userid;

    public ModelClass(String name,String email,String userid){

        this.name=name;
        this.email=email;
        this.userid=userid;
    }

    public String getName() {
        return name;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public ModelClass(){


    }
}
