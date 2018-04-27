package com.example.javierdiaz.demooxcom.beans;

import java.util.List;

/**
 * Created by SinAsignarT1 on 13/11/2017.
 */

public class EfectoAdverso {

    private String titulo;
    private String recomendacion;
    List<Grado> grados;

    public List<Grado> getGrado() {
        return grados;
    }

    public void setGrado(List<Grado> grado) {
        this.grados = grado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
}
