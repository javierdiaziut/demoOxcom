package com.example.javierdiaz.demooxcom.beans;

/**
 * Created by SinAsignarT1 on 19/12/2017.
 */

public class GeneralResponseLogin {
    private int success;
    private String error;

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
}
