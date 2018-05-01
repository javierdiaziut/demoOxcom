package com.example.javierdiaz.demooxcom.activities;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.EfectoAdverso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BaseActivity extends AppCompatActivity {

    AlertDialog progressDialog;
    protected static ArrayList<EfectoAdverso>  mEfectoAdversos;
    protected static String mSelectedProblema;
    protected static String mSelectedMedicina;


    /**
     * This function create and display the activity represented by cls
     *
     * @param cls class of the Activity that will be displayed
     */
    public void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }

    public void navigateToActivityWithNoHistory(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


    /**
     * This function create and display the activity represented by cls
     *
     * @param activity  class of the Activity that will be displayed
     * @param extraData String extra parameter that will be sent to the Activity that will be displayed
     */
    public void navigateToActivityPassingVars(Class<?> activity, HashMap<String, String> extraData) {
        Intent intent = new Intent(getApplicationContext(), activity);
        for (Map.Entry<String, String> entry : extraData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            intent.putExtra(key, value);
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void showDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.progress_bar, null);
            dialogBuilder.setView(dialogView);
            progressDialog = dialogBuilder.create();
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public void showDismissDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            showDialog();
        } else {
            dismissDialog();
        }
    }

}
