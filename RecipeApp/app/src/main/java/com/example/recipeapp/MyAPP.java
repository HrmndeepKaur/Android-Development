package com.example.recipeapp;

import android.app.Application;

public class MyAPP extends Application {
    NetworkingService networkingService = new NetworkingService();
    JSON_Service jsonService = new JSON_Service();

    public JSON_Service getJsonService(){
        return jsonService;
    }
    public NetworkingService getNetworkingService(){
        return networkingService;
    }
}
