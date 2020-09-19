package com.example.w4118project.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w4118project.db.MascotasBuilder;
import com.example.w4118project.pojo.Mascota;
import com.example.w4118project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;
    private boolean enableUI;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
        this.enableUI = true;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.get().load(Integer.parseInt(mascota.getUrlFoto())).into(holder.imgFoto);
        holder.tvNombre.setText(mascota.getNombre());
        holder.rating.setText(String.valueOf(mascota.getRating()));

        holder.imgHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enableUI) {
                    Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

                    MascotasBuilder mascotasBuilder = new MascotasBuilder(activity);
                    mascotasBuilder.darRating(mascota);

                    holder.rating.setText(String.valueOf(mascotasBuilder.obtenerRating(mascota)));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mascotas.size();
    }

    public boolean isEnableUI() {
        return enableUI;
    }

    public void setEnableUI(boolean enableUI) {
        this.enableUI = enableUI;
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private ImageView imgHueso;
        private TextView tvNombre;
        private TextView rating;


        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgFoto = (ImageView) itemView.findViewById(R.id.iv_mascota_foto);
            this.imgHueso = (ImageView) itemView.findViewById(R.id.iv_white_bone);
            this.tvNombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            this.rating = (TextView) itemView.findViewById(R.id.tv_rating);

        }
    }
}
