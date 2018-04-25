package com.example.javierdiaz.demooxcom.interfaces;

import com.example.javierdiaz.demooxcom.beans.GeneralResponseLogin;
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

    @POST("login.php")
    @FormUrlEncoded
    Call<GeneralResponseLogin> login(@Field("user") String user, @Field("pass") String pass, @Field("codeAPPRoche") int codeAPPRoche);

    @POST("ingresarSolicitud.php")
    @FormUrlEncoded
    Call<GeneralResponseLogin> crearSolicitud(@Field("nombre") String nombre, @Field("apellido") String apellido, @Field("email") String email, @Field("codeAPPRoche") int codeAPPRoche);

    @POST("recuperarPass.php")
    @FormUrlEncoded
    Call<GeneralResponseLogin> recuperarPass(@Field("email") String email, @Field("codeAPPRoche") int codeAPPRoche);

    @POST("getUltimaActua.php")
    @FormUrlEncoded
    Call<GeneralResponseLogin> getUltimaActua(@Field("codeAPPRoche") int codeAPPRoche);
}
