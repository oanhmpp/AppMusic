package com.example.appmusic.Service;

// function ket hop
public class APIServer {
    private static String base_url = "https://oanhnguyen1999.000webhostapp.com/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
