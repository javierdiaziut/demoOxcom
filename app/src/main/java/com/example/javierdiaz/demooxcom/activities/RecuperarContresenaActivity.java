package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.GeneralResponseLogin;
import com.example.javierdiaz.demooxcom.databinding.ActivityRecuperarContresenaBinding;
import com.example.javierdiaz.demooxcom.interfaces.APIService;
import com.example.javierdiaz.demooxcom.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarContresenaActivity extends BaseActivity {
    ActivityRecuperarContresenaBinding binding;
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recuperar_contresena);
        mApiService = ApiUtils.getAPIService();

        binding.buttonRecuperarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail()){
                    recuperarContraseña();
                }

            }
        });

        binding.buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private boolean validateEmail(){
        if (binding.editTextEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Email",Toast.LENGTH_LONG).show();
            return false;
        }else if (binding.editTextConfirmarEmail.getText().toString().trim().isEmpty()){
                Toast.makeText(getApplicationContext(),"Por favor confirmar Email",Toast.LENGTH_LONG).show();
                return false;
        }else if (!binding.editTextEmail.getText().toString().
                trim().equalsIgnoreCase(binding.editTextConfirmarEmail.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Emails no son iguales, favor verificar",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
    }

    public void recuperarContraseña() {
        mApiService.recuperarPass(binding.editTextEmail.getText().toString(), 20171010).enqueue(new Callback<GeneralResponseLogin>() {
            @Override
            public void onResponse(Call<GeneralResponseLogin> call, Response<GeneralResponseLogin> response) {
                if(response.isSuccessful()) {
                    if(response.body().getSuccess() == 1){
                        Toast.makeText(getApplicationContext(),"Se ha enviado un email",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Email no registrado",Toast.LENGTH_LONG).show();
                    }
                }else{
                    //Log.i("ingresarSolicitud ERROR", "post submitted to API." + response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<GeneralResponseLogin> call, Throwable t) {
                //Log.e("ingresarSolicitud FAIL", "Unable to submit post to API.");

            }
        });
    }
}
