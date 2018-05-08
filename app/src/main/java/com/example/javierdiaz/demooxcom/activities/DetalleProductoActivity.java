package com.example.javierdiaz.demooxcom.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.javierdiaz.demooxcom.R;
import com.example.javierdiaz.demooxcom.beans.EfectoAdverso;
import com.example.javierdiaz.demooxcom.beans.Grado;
import com.example.javierdiaz.demooxcom.beans.LineaTerapeutica;
import com.example.javierdiaz.demooxcom.databinding.ActivityDetalleProductoBinding;

import java.util.ArrayList;

public class DetalleProductoActivity extends BaseActivity {

    ActivityDetalleProductoBinding binding;
    ArrayList<Grado> mGrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_producto);
        if(this.getIntent().getExtras()!= null){
            Bundle b = this.getIntent().getExtras();
            mGrados= (ArrayList<Grado>) b.getSerializable("grades");
        }



        updateDataScreen(mGrados.get(0));
        binding.textViewMainTitle.setText(mSelectedProblema);
        binding.includedAppBarTitle.textViewProductos.setText(mSelectedMedicina);
        binding.btnGrade1.setOnClickListener(mOnClickListenerGrade1);
        binding.btnGrade2.setOnClickListener(mOnClickListenerGrade2);
        binding.btnGrade3.setOnClickListener(mOnClickListenerGrade3);
        binding.btnGrade4.setOnClickListener(mOnClickListenerGrade4);


        binding.btnGrade1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.btnGrade1.setPressed(true);
                binding.btnGrade1.performClick();
                binding.btnGrade4.setPressed(false);
                binding.btnGrade3.setPressed(false);
                binding.btnGrade2.setPressed(false);
                return true;
            }
        });
        binding.btnGrade2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.btnGrade2.setPressed(true);
                binding.btnGrade2.performClick();
                binding.btnGrade4.setPressed(false);
                binding.btnGrade3.setPressed(false);
                binding.btnGrade1.setPressed(false);
                return true;
            }
        });
        binding.btnGrade3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.btnGrade3.setPressed(true);
                binding.btnGrade3.performClick();
                binding.btnGrade4.setPressed(false);
                binding.btnGrade2.setPressed(false);
                binding.btnGrade1.setPressed(false);
                return true;
            }
        });
        binding.btnGrade4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.btnGrade4.setPressed(true);
                binding.btnGrade4.performClick();
                binding.btnGrade3.setPressed(false);
                binding.btnGrade2.setPressed(false);
                binding.btnGrade1.setPressed(false);
                return true;
            }
        });

        binding.includedAppBarTitle.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.includedAppBarTitle.btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnGrade1.setPressed(true);
    }

    private void updateDataScreen(Grado mgrado) {
        binding.textViewCriterioDesc.setText(mgrado.getCriterio());
        binding.textViewManejoDesc.setText(mgrado.getManejo());
    }

    View.OnClickListener mOnClickListenerGrade1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mGrados.get(0) != null)
            updateDataScreen(mGrados.get(0));
        }
    };

    View.OnClickListener mOnClickListenerGrade2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if((mGrados.get(1) != null))
            updateDataScreen(mGrados.get(1));
        }
    };
    View.OnClickListener mOnClickListenerGrade3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mGrados.get(2) != null)
            updateDataScreen(mGrados.get(2));
        }
    };
    View.OnClickListener mOnClickListenerGrade4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mGrados.get(3) != null)
            updateDataScreen(mGrados.get(3));
        }
    };
}
