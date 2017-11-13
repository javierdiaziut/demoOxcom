package com.example.javierdiaz.demooxcom.beans;

import java.io.Serializable;

/**
 * Created by Javier on 12/11/2017.
 */
public class Grado implements Serializable {
    int orden;
    String criterio;
    String manejo;

    public Grado() {
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getManejo() {
        return manejo;
    }

    public void setManejo(String manejo) {
        this.manejo = manejo;
    }
}
