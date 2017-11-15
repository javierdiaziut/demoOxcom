package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityDetalleMedicinaBinding;

import java.util.ArrayList;

public class DetalleMedicinaActivity extends Activity {
    ActivityDetalleMedicinaBinding binding;
    ArrayList<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_medicina);
        getItemList();
        ListGuiaESAdapter mListGuiaESAdapter = new ListGuiaESAdapter(this, R.layout.item_guia_es, values);
        binding.listMedicamentos.setAdapter(mListGuiaESAdapter);
        binding.listMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 1"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 2"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Tab 3"));
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);

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


}
