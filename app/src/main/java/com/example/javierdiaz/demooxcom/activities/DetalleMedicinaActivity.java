package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.EfectoAdverso;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityDetalleMedicinaBinding;

import java.util.ArrayList;

public class DetalleMedicinaActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    ActivityDetalleMedicinaBinding binding;
    ArrayList<String> values;
    final int[] ICONS = new int[]{
            R.mipmap.danger,
            R.mipmap.user,
            R.mipmap.settings
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_medicina);
        getItemList();
        ListGuiaESAdapter mListGuiaESAdapter = new ListGuiaESAdapter(this, R.layout.item_guia_es, values);
        binding.includedAppBarTitle.textViewTitle.setText(GuiaEfectosAdversosActivity.nombreLineaSeleccionada);
        binding.listMedicamentos.setAdapter(mListGuiaESAdapter);
        binding.listMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mEfectoAdversos = new EfectoAdverso();
                //TODO OJO CON ESTO
                if((GuiaEfectosAdversosActivity.mProductosLinea.size()> position)&&(GuiaEfectosAdversosActivity.mProductosLinea.get(position) != null)){
                    //mEfectoAdversos = GuiaEfectosAdversosActivity.mProductosLinea.get(position).getEfectoAdverso();
                    mSelectedProblema = values.get(position);
                    navigateToActivity(DetalleProductoActivity.class);
                }else{
                    Toast.makeText(getApplicationContext(), "No disponible",Toast.LENGTH_SHORT).show();
                }


            }
        });
        binding.tabs.addTab(binding.tabs.newTab().setText(""));
        binding.tabs.addTab(binding.tabs.newTab().setText(""));
        binding.tabs.addTab(binding.tabs.newTab().setText(""));
        binding.tabs.getTabAt(0).setIcon(ICONS[0]);
        binding.tabs.getTabAt(1).setIcon(ICONS[1]);
        binding.tabs.getTabAt(2).setIcon(ICONS[2]);
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        binding.tabs.setSelected(false);
        binding.tabs.getTabAt(1).select();
        binding.tabs.addOnTabSelectedListener(this);

        binding.includedAppBarTitle.btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void getItemList(){
        values = new ArrayList<>();
        values.add("Hipertension");
        values.add("Eventos tromboembolicos venosos (VTE)");
        values.add("Prblema de cicatrizacion");
        values.add("Proteinuiria");
        values.add("Episodios hemorragicos");
        values.add("Eventos tromboembolicos arteriales (ATE)");
        values.add("Perforacion gastrointestinal (GI)");

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0){
            navigateToActivityWithNoHistory(ReportarEventoActivity.class);
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
