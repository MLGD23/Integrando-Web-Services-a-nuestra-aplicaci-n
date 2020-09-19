package com.example.w4118project.restAPI;

import com.example.w4118project.restAPI.model.MascotaResponse;
import com.example.w4118project.restAPI.model.PerfilResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndpointsAPI {

    @GET(ConstantesRestAPI.URL_GET_RECENT_MEDIA)
    public Call<MascotaResponse> getRecentMedia(@Path("user") String user);

    @GET(ConstantesRestAPI.URL_GET_USER_PROFILE)
    public Call<PerfilResponse> getPerfil(@Path("user") String user);
}
