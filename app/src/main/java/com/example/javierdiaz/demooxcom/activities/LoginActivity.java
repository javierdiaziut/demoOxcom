package com.example.javierdiaz.demooxcom.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.DatosApp;
import com.example.javierdiaz.demooxcom.beans.GeneralRequestResponse;
import com.example.javierdiaz.demooxcom.beans.GeneralResponseLogin;
import com.example.javierdiaz.demooxcom.beans.LoginBean;
import com.example.javierdiaz.demooxcom.databinding.ActivityLogInBinding;
import com.example.javierdiaz.demooxcom.interfaces.APIService;
import com.example.javierdiaz.demooxcom.retrofit.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    ActivityLogInBinding binding;
    public static DatosApp mDatosApp;
    private APIService mApiService;
    //private APIServiceNoURL mApiServiceNoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        mApiService = ApiUtils.getAPIService();
        getUltimaActualizacion(20171010);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFileds()){
                    LoginBean mLoginBean = new LoginBean(binding.editTextUser.getText().toString(),
                            binding.editTextPassword.getText().toString(),20171010);
                    sendPost(mLoginBean);
                }

            }
        });

        binding.textViewRequestAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(SolicitarAccesoActivity.class);
//                if(validateEmail()){
//                    ingresarSolicitud();
//                }
            }
        });

        binding.textViewForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail()){
                    recuperarContraseña();
                }
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("datos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void sendPost(LoginBean body) {
        mApiService.login(body.getUser(),body.getPass(),body.getCodeAPPRoche()).enqueue(new Callback<GeneralResponseLogin>() {
            @Override
            public void onResponse(Call<GeneralResponseLogin> call, Response<GeneralResponseLogin> response) {
                if(response.isSuccessful()) {
                    Log.i("LOGIN SUCCESS", "post submitted to API." + response.body().toString());
                    if(response.body().getSuccess() == 1){
                        navigateToActivity(GuiaEfectosAdversosActivity.class);
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.i("LOGIN ERROR", "post submitted to API." + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<GeneralResponseLogin> call, Throwable t) {
                Log.e("LOGIN FAIL", "Unable to submit post to API.");

            }
        });
    }

    public void getUltimaActualizacion(int codeAPPRoche) {
        mApiService.getUltimaActua(codeAPPRoche).enqueue(new Callback<GeneralRequestResponse>() {
            @Override
            public void onResponse(Call<GeneralRequestResponse> call, Response<GeneralRequestResponse> response) {
                if(response.isSuccessful()) {
                    Log.i("GETULTIMA SUCCESS", "post submitted to API." + response.body().toString());
                    if(response.body().getSuccess() == 1){
                        getJsonFromURL(response.body().getUrl());
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.i("GETULTIMA ERROR", "post submitted to API." + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<GeneralRequestResponse> call, Throwable t) {
                Log.i("GETULTIMA FAIL", "Unable to submit post to API.");

            }
        });
    }

    public void getJsonFromURL(String url) {
        mApiService.getJsonFromUrl(url).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    Log.i("getJsonFromUrl SUCCESS", "post submitted to API." + response.body().toString());
                    try {
                        JSONObject obj = new JSONObject(response.body().toString());

                        Gson gson = new Gson();
                        Type listType = new TypeToken<DatosApp>(){}.getType();
                        mDatosApp = gson.fromJson(obj.toString(), listType);
                        Log.i("datosapp", mDatosApp.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Log.i("getJsonFromUrl ERROR", "post submitted to API.");
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("getJsonFromUrl FAIL", "Unable to submit post to API.");

            }
        });
    }

    private boolean validateFileds(){
        if (binding.editTextUser.getText().toString().trim().isEmpty() ||
                binding.editTextPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Usuario/Contraseña",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    private boolean validateEmail(){
        if (binding.editTextUser.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Email",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }



    public void recuperarContraseña() {
        mApiService.recuperarPass(binding.editTextUser.getText().toString(), 20171010).enqueue(new Callback<GeneralResponseLogin>() {
            @Override
            public void onResponse(Call<GeneralResponseLogin> call, Response<GeneralResponseLogin> response) {
                if(response.isSuccessful()) {
                    if(response.body().getSuccess() == 1){
                        Toast.makeText(getApplicationContext(),"Se ha enviado un email",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Email no registrado",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Log.i("ingresarSolicitud ERROR", "post submitted to API." + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<GeneralResponseLogin> call, Throwable t) {
                Log.e("ingresarSolicitud FAIL", "Unable to submit post to API.");

            }
        });
    }


}
