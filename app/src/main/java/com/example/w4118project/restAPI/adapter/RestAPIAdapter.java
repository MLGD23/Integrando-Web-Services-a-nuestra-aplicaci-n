package com.example.w4118project.restAPI.adapter;

import com.example.w4118project.restAPI.ConstantesRestAPI;
import com.example.w4118project.restAPI.EndpointsAPI;
import com.example.w4118project.restAPI.deserializer.MascotaDeserializer;
import com.example.w4118project.restAPI.deserializer.PerfilDeserializer;
import com.example.w4118project.restAPI.model.MascotaResponse;
import com.example.w4118project.restAPI.model.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIAdapter {

    public EndpointsAPI establecerConexionRestAPIInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsAPI.class);
    }

    public Gson construirGsonDeserializadorRecentMedia() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializer());
        return gsonBuilder.create();
    }

    public Gson construirGsonDeserializadorPerfil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PerfilResponse.class, new PerfilDeserializer());
        return gsonBuilder.create();
    }
}
