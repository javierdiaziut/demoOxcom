package com.example.javierdiaz.demooxcom.beans;

/**
 * Created by Javier on 26/04/2018.
 */

public class GeneralRequestResponse {
    private int success;
    private String error;
    private String fecha;
    private String url;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
