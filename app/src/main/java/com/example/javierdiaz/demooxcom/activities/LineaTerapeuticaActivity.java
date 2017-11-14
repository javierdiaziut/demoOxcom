package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityLineaTerapeuticaBinding;

import java.util.ArrayList;

public class LineaTerapeuticaActivity extends BaseActivity {
    ActivityLineaTerapeuticaBinding binding;
    ArrayList<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_linea_terapeutica);
        getNameItems();
        binding.textTitleLinea.setText(GuiaEfectosAdversosActivity.nombreLineaSeleccionada);
        ListGuiaESAdapter mListGuiaESAdapter = new ListGuiaESAdapter(this, R.layout.item_guia_es, values);
        binding.listLineaTerapeutica.setAdapter(mListGuiaESAdapter);
        binding.listLineaTerapeutica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });


        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 1"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 2"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 3"));
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void getNameItems(){
        values = new ArrayList<>();
        for (int i =0; i < GuiaEfectosAdversosActivity.mProductosLinea.size(); i++){
            values.add( GuiaEfectosAdversosActivity.mProductosLinea.get(i).getNombreProducto());
        }
    }
}
