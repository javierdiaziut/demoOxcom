package com.example.javierdiaz.demooxcom.activities;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.databinding.ActivityReportarEventoBinding;

public class ReportarEventoActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    ActivityReportarEventoBinding binding;
    final int[] ICONS = new int[]{
            R.mipmap.guia,
            R.mipmap.user,
            R.mipmap.danger
    };

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

        binding.btnEnviarAhora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendEmail("chile.farmacovigilancia@roche.com",getString(R.string.subject_reporte_evento),getString(R.string.dummie_email_reporte));
            }
        });
        binding.btnLlamarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDial();
            }
        });

        binding.textViewPolitica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTermsAndPrivacy();
            }
        });

        binding.tabs.addTab(binding.tabs.newTab().setText("Guia"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Usuario"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Reportar"));
        binding.tabs.getTabAt(0).setIcon(ICONS[0]);
        binding.tabs.getTabAt(1).setIcon(ICONS[1]);
        binding.tabs.getTabAt(2).setIcon(ICONS[2]);;
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        binding.tabs.setSelected(false);
        binding.tabs.getTabAt(2).select();
        binding.tabs.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0){
            navigateToActivityWithNoHistory(GuiaEfectosAdversosActivity.class);
        }else if (tab.getPosition() == 1){
            navigateToActivityWithNoHistory(ProfileActivity.class);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
