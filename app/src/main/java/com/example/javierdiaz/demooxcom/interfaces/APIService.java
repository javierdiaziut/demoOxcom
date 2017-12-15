package com.example.javierdiaz.demooxcom.interfaces;

import com.example.javierdiaz.demooxcom.beans.LoginBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Javier on 14/12/2017.
 */

public interface APIService {

    @POST("/login.php")
    @FormUrlEncoded
    Call<LoginBean> login(@Body String body);
}
