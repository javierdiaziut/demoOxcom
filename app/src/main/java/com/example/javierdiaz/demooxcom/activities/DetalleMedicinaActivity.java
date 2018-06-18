package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.adapters.ListGuiaESAdapter;
import com.example.javierdiaz.demooxcom.beans.EfectoAdverso;
import com.example.javierdiaz.demooxcom.beans.Grado;
import com.example.javierdiaz.demooxcom.beans.Productos;
import com.example.javierdiaz.demooxcom.databinding.ActivityDetalleMedicinaBinding;

import java.util.ArrayList;

public class DetalleMedicinaActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    ActivityDetalleMedicinaBinding binding;
    ArrayList<String> values;
    final int[] ICONS = new int[]{
            R.mipmap.guia,
            R.mipmap.user,
            R.mipmap.danger
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
                //mEfectoAdversos = new ArrayList<>();
                //TODO OJO CON ESTO
                try{
                    ArrayList<Grado> grades = new ArrayList<>();
                    grades = (ArrayList<Grado>) mSelectedProducto.getEfectoAdverso().get(position).getGrado();
                    Bundle b = new Bundle();
                    b.putSerializable("grades", grades);
                    Intent i=new Intent(getApplicationContext(), DetalleProductoActivity.class);
                    i.putExtras(b);
                    startActivity(i);

                }catch (Exception E){
                    Toast.makeText(getApplicationContext(), "No disponible",Toast.LENGTH_SHORT).show();
                }

//                if((GuiaEfectosAdversosActivity.mProductosLinea.size()> position)&&(GuiaEfectosAdversosActivity.mProductosLinea.get(position) != null)){
//                    mEfectoAdversos = (ArrayList)GuiaEfectosAdversosActivity.mProductosLinea.get(position).getEfectoAdverso();
//                    mSelectedProblema = values.get(position);
//                    navigateToActivity(DetalleProductoActivity.class);
//                }else{
//
//                }


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
        try{
            loadImage(imageToLoad, binding.imgDetailProduct);
        }catch (Exception e){

        }

    }

    private void getItemList(){
        values = new ArrayList<>();
        if(mEfectoAdversos != null){
            for(EfectoAdverso mEfectoAdverso: mEfectoAdversos){
                values.add(mEfectoAdverso.getTitulo());
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
