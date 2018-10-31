package com.example.padcc.retrofitlibraryapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    String BASE_URL = "https://api.androidhive.info/";

    @GET("contacts")
    Call<Contacts> getHeroes();
}
