package com.example.javierdiaz.demooxcom.interfaces;

import com.example.javierdiaz.demooxcom.beans.GeneralRequestResponse;
import com.example.javierdiaz.demooxcom.beans.GeneralResponseLogin;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;


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
    Call<GeneralRequestResponse> getUltimaActua(@Field("codeAPPRoche") int codeAPPRoche);

    @GET()
    Call<JsonObject> getJsonFromUrl(@Url String url);


}
