package com.example.javierdiaz.demooxcom.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;


import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityGuiaEfectosAdversosBinding;

import java.util.ArrayList;

public class GuiaEfectosAdversosActivity extends BaseActivity {

    ActivityGuiaEfectosAdversosBinding binding;
    ArrayList<String> values;
    public static String nombreLineaSeleccionada;
    public static ArrayList<Productos> mProductosLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guia_efectos_adversos);
        binding.includedAppBarTitle.textViewTitle.setText(getString(R.string.title_guia_efectos_adversos));
        getNameItems();

        ListGuiaESAdapter mListGuiaESAdapter = new ListGuiaESAdapter(this, R.layout.item_guia_es, values);
        binding.listGuiaEfectosAdversos.setAdapter(mListGuiaESAdapter);
        binding.listGuiaEfectosAdversos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                nombreLineaSeleccionada = LoginActivity.mDatosApp.getDatosApp().getLineaTerapeutica().get(position).getNombreLinea();
                mProductosLinea = (ArrayList<Productos>) LoginActivity.mDatosApp.getDatosApp().getLineaTerapeutica().get(position).getProductos();
                navigateToActivity(LineaTerapeuticaActivity.class);
            }
        });


        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 1"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 2"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 3"));
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void getNameItems(){
        values = new ArrayList<>();
        for (int i =0; i < LoginActivity.mDatosApp.getDatosApp().getLineaTerapeutica().size(); i++){
            values.add( LoginActivity.mDatosApp.getDatosApp().getLineaTerapeutica().get(i).getNombreLinea());
        }
    }


}
