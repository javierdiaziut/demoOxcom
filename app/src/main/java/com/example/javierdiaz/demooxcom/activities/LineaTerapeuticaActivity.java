package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.EfectoAdverso;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityLineaTerapeuticaBinding;

import java.util.ArrayList;

public class LineaTerapeuticaActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{
    ActivityLineaTerapeuticaBinding binding;
//    public static ArrayList<EfectoAdverso> mSelectedEfectoAdverso;
    ArrayList<String> values;
    final int[] ICONS = new int[]{
            R.mipmap.guia,
            R.mipmap.user,
            R.mipmap.danger
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_linea_terapeutica);
        getNameItems();
        binding.textTitleLinea.setText(GuiaEfectosAdversosActivity.nombreLineaSeleccionada);
        binding.includedAppBarTitle.textViewTitle.setText(getString(R.string.linea_terapeutica));
        ListGuiaESAdapter mListGuiaESAdapter = new ListGuiaESAdapter(this, R.layout.item_guia_es, values);
        binding.listLineaTerapeutica.setAdapter(mListGuiaESAdapter);
        binding.listLineaTerapeutica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mSelectedMedicina = values.get(position);
                mEfectoAdversos = (ArrayList<EfectoAdverso>) GuiaEfectosAdversosActivity.mProductosLinea.get(position).getEfectoAdverso();

                navigateToActivity(DetalleMedicinaActivity.class);
            }
        });
        binding.textAreaHiperLink.setOnClickListener(new View.OnClickListener() {
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
        binding.tabs.getTabAt(0).select();
        binding.tabs.addOnTabSelectedListener(this);

        binding.includedAppBarTitle.btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void getNameItems(){
        values = new ArrayList<>();
        if(GuiaEfectosAdversosActivity.mProductosLinea != null){
            for (int i =0; i < GuiaEfectosAdversosActivity.mProductosLinea.size(); i++){
                values.add( GuiaEfectosAdversosActivity.mProductosLinea.get(i).getNombreProducto());
            }
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 2){
            navigateToActivityWithNoHistory(ReportarEventoActivity.class);
        }else if (tab.getPosition() == 1){
            navigateToActivityWithNoHistory(ProfileActivity.class);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0){
            navigateToActivityWithNoHistory(ReportarEventoActivity.class);
        }

    }
}
