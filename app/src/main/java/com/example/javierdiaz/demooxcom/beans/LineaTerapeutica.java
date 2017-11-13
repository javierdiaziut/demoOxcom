package com.example.javierdiaz.demooxcom.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 12/11/2017.
 */
public class LineaTerapeutica {
    @SerializedName("nombreLinea")
    @Expose
    private String nombreLinea;
    @SerializedName("Productos")
    @Expose
    private List<Productos> productos = null;

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
}
