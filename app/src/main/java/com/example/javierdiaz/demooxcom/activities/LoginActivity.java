package com.example.javierdiaz.demooxcom.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.DatosApp;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.databinding.ActivityLogInBinding;
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

public class LoginActivity extends BaseActivity {
    ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            Log.i("JSON",obj.toString());

            Gson gson = new Gson();
            Type listType = new TypeToken<LineaTerapeutica>(){}.getType();
            DatosApp mDatosApp = gson.fromJson(obj.toString(), listType);
            Log.i("PARSE",mDatosApp.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(GuiaEfectosAdversosActivity.class);
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
}
