package com.example.javierdiaz.demooxcom.retrofit;

import com.example.javierdiaz.demooxcom.beans.URL;
import com.example.javierdiaz.demooxcom.interfaces.APIService;

/**
 * Created by Javier on 17/12/2017.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static APIService getAPIService() {

        return RetrofitClient.getClient(URL.URL_BASE).create(APIService.class);
    }
}
