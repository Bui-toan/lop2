package com.example.lop2.services;

public class ApiService {
    private static  String  base_url = "https://liam-db.000webhostapp.com/Server/";

    public static DataService getService(){
        return ApiRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
