package com.example.javierdiaz.demooxcom.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 12/11/2017.
 */
public class Productos implements Serializable {

    String foto;
    String nombreProducto;
    EfectoAdverso efectoAdverso;

    public Productos() {
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public EfectoAdverso getEfectoAdverso() {
        return efectoAdverso;
    }

    public void setEfectoAdverso(EfectoAdverso efectoAdverso) {
        this.efectoAdverso = efectoAdverso;
    }
}
