package com.example.javierdiaz.demooxcom.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.DataApp;
import com.example.javierdiaz.demooxcom.beans.DatosApp;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.beans.LoginBean;
import com.example.javierdiaz.demooxcom.databinding.ActivityLogInBinding;
import com.example.javierdiaz.demooxcom.interfaces.APIService;
import com.example.javierdiaz.demooxcom.retrofit.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    ActivityLogInBinding binding;
    public static DataApp mDatosApp;
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        mApiService = ApiUtils.getAPIService();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            Gson gson = new Gson();
            Type listType = new TypeToken<DataApp>(){}.getType();
            mDatosApp = gson.fromJson(obj.toString(), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFileds()){
                    LoginBean mLoginBean = new LoginBean(binding.editTextUser.getText().toString(),
                            binding.editTextPassword.getText().toString(),20171010);
                    sendPost(LoginBean.parseObject(mLoginBean));
                }
                //navigateToActivity(GuiaEfectosAdversosActivity.class);
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

    public void sendPost(String body) {
        mApiService.login(body).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if(response.isSuccessful()) {
                    Log.i("LOGIN SUCCESS", "post submitted to API." + response.body().toString());
                }else{
                    Log.i("LOGIN ERROR", "post submitted to API." + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.e("LOGIN FAIL", "Unable to submit post to API.");

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


}
