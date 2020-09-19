package com.example.w4118project.restAPI.deserializer;

import android.util.Log;

import com.example.w4118project.restAPI.JsonKeys;
import com.example.w4118project.restAPI.model.MascotaResponse;
import com.example.w4118project.restAPI.model.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class PerfilDeserializer implements JsonDeserializer<PerfilResponse> {

    @Override
    public PerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PerfilResponse perfilResponse = gson.fromJson(json, PerfilResponse.class);

        JsonObject data = json.getAsJsonObject();

        perfilResponse.setId(data.get(JsonKeys.USER_ID).getAsString());
        perfilResponse.setUserName(data.get(JsonKeys.USER_NAME).getAsString());

        return perfilResponse;
    }
}
