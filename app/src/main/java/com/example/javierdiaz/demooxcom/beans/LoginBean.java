package com.example.javierdiaz.demooxcom.beans;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Javier on 14/12/2017.
 */

public class LoginBean {

    public String user;
    public String pass;
    public int codeAPPRoche;

    public LoginBean(String user, String pass, int codeAPPRoche) {
        this.user = user;
        this.pass = pass;
        this.codeAPPRoche = codeAPPRoche;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getCodeAPPRoche() {
        return codeAPPRoche;
    }

    public void setCodeAPPRoche(int codeAPPRoche) {
        this.codeAPPRoche = codeAPPRoche;
    }

    public static String parseObject(LoginBean mLoginBean){
        String mJsonObject;
        Gson gson = new Gson();
        mJsonObject = gson.toJson(mLoginBean);
        return mJsonObject;
    }
}
