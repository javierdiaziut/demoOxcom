package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.GeneralResponseLogin;
import com.example.javierdiaz.demooxcom.databinding.ActivitySolicitarAccesoBinding;
import com.example.javierdiaz.demooxcom.interfaces.APIService;
import com.example.javierdiaz.demooxcom.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitarAccesoActivity extends BaseActivity{
    ActivitySolicitarAccesoBinding binding;
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_solicitar_acceso);
        mApiService = ApiUtils.getAPIService();

        binding.buttonSolicitarAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    ingresarSolicitud();
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

    private boolean validateFields(){
        boolean ans = Boolean.TRUE;
        if (binding.editTextNombre.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Nombre",Toast.LENGTH_LONG).show();
            ans = false;
            return ans;
        }
        if (binding.editTextApellido.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Apellido",Toast.LENGTH_LONG).show();
            ans = false;
            return ans;
        }
        if (binding.editTextRut.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Rut",Toast.LENGTH_LONG).show();
            ans = false;
            return ans;
        }
        if (binding.editTextEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Email",Toast.LENGTH_LONG).show();
            ans = false;
            return ans;
        }
        if (binding.editTextConfirmarEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Por favor introduzca Confirmación de Email",Toast.LENGTH_LONG).show();
            ans = false;
            return ans;
        }
        return ans;
    }

    public void ingresarSolicitud() {
        mApiService.crearSolicitud(binding.editTextNombre.getText().toString(),binding.editTextApellido.getText().toString(),
                binding.editTextRut.getText().toString(),binding.editTextEmail.getText().toString(), 20171010).enqueue(new Callback<GeneralResponseLogin>() {
            @Override
            public void onResponse(Call<GeneralResponseLogin> call, Response<GeneralResponseLogin> response) {
                if(response.isSuccessful()) {
                    if(response.body().getSuccess() == 1){
                        Toast.makeText(getApplicationContext(),"Solicitud enviada",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Solicitud no enviada",Toast.LENGTH_LONG).show();
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
