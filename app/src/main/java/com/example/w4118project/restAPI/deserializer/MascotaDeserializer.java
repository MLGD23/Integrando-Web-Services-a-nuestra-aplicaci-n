package com.example.w4118project.restAPI.deserializer;

import android.util.Log;

import com.example.w4118project.pojo.Mascota;
import com.example.w4118project.restAPI.JsonKeys;
import com.example.w4118project.restAPI.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDeserializer implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.i("TAG", "[MascotaDeserializer.deserialize(...)]");
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);

        JsonArray data = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        Log.i("TAG", "[MascotaDeserializer.deserialize(...)] data: " + data);

        mascotaResponse.setMascotas(deserializarMascotaDeJSON(data));
        Log.i("TAG", "[MascotaDeserializer.deserialize(...)] mascotaResponse.getMascotas(): " + mascotaResponse.getMascotas());
        //Log.i("TAG", "[MascotaDeserializer.deserialize(...)] mascotaResponse.getMascotas().size(): " + mascotaResponse.getMascotas().size());
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotaDeJSON(JsonArray jsonArray) {
        Log.i("TAG", "[MascotaDeserializer.deserializarMascotaDeJSON(...)]");
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject dataJSON = jsonArray.get(i).getAsJsonObject();
            String id = dataJSON.get(JsonKeys.USER_ID).getAsString();
            String userName = dataJSON.get(JsonKeys.USER_NAME).getAsString();
            String urlFoto = dataJSON.get(JsonKeys.MEDIA_URL).getAsString();
            String mediaType = dataJSON.get(JsonKeys.MEDIA_TYPE).getAsString();
            //Since new Instagram API no longer supports retrieving likes, the Caption length is used as temporary workaround
            int rating = dataJSON.get(JsonKeys.MEDIA_CAPTION) == null ? 0 : dataJSON.get(JsonKeys.MEDIA_CAPTION).getAsString().length();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombre(userName);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setRating(rating);

            if (!mediaType.equals("IMAGE")) {
                continue;
            }

            mascotas.add(mascotaActual);
        }

        Log.i("TAG", "[MascotaDeserializer.deserializarMascotaDeJSON(...)] mascotas: " + mascotas);
        Log.i("TAG", "[MascotaDeserializer.deserializarMascotaDeJSON(...)] mascotas.size(): " + mascotas.size());
        return mascotas;
    }
}
