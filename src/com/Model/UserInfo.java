package com.Model;

import java.util.Random;

public class UserInfo {
    private Integer user_id;
    private String user_name;
    private String user_hash;
    private String user_salt;


    public UserInfo(){

    }
    public UserInfo( Integer user_id, String user_name, String user_hash, String user_salt){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_hash = user_hash;
        this.user_salt = user_salt;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_hash(String user_hash) {
        this.user_hash = user_hash;
    }

    public void setUser_salt(String user_salt) {
        this.user_salt = user_salt;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_hash() {
        return user_hash;
    }

    public String getUser_salt() {
        return user_salt;
    }
    public String getUser_name(){
        return user_name;
    }

    public static String addSalt(){
        Random r = new Random();
        String saltPool = "&*^%$#><#$&";
        int temp = r.nextInt(10) + 1;

        return saltPool.substring(temp,temp + 1);
    }
}
