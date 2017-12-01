package com.example.javierdiaz.demooxcom.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.databinding.ActivityReportarEventoBinding;

public class ReportarEventoActivity extends BaseActivity {

    ActivityReportarEventoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reportar_evento);
        binding.includedAppBarTitle.textViewProductos.setText(getString(R.string.reportar_efecto_adverso));

        binding.includedAppBarTitle.btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
