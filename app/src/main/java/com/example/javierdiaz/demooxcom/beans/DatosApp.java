package com.example.javierdiaz.demooxcom.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Javier on 12/11/2017.
 */
public class DatosApp {
    @Expose
    private List<LineaTerapeutica> lineaTerapeutica = null;

    public List<LineaTerapeutica> getLineaTerapeutica() {
        return lineaTerapeutica;
    }

    public void setLineaTerapeutica(List<LineaTerapeutica> lineaTerapeutica) {
        this.lineaTerapeutica = lineaTerapeutica;
    }
}
