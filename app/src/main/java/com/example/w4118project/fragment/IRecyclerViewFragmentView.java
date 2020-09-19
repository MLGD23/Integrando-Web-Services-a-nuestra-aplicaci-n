package com.example.w4118project.fragment;

import com.example.w4118project.adapter.MascotaAdapter;
import com.example.w4118project.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdapter crearMascotaAdapter(ArrayList<Mascota> mascotas);

    public void inicializarAdapterRV(MascotaAdapter mascotaAdapter);
}
