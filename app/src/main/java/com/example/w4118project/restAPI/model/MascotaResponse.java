package com.example.w4118project.restAPI.model;

import com.example.w4118project.pojo.Mascota;

import java.util.ArrayList;

public class MascotaResponse {

    private ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
