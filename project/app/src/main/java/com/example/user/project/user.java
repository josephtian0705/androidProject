package com.example.user.project;

public class user {

    String username, age, gender;

    public user(){


    }

    public user(String username,String age, String gender){

        this.username = username;
        this.age = age;
        this.gender = gender;

    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {

        return username;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
