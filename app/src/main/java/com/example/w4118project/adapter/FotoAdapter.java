package com.example.w4118project.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.w4118project.R;
import com.example.w4118project.pojo.Foto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.FotoViewHolder> {

    private ArrayList<Foto> fotos;
    private Activity activity;

    public FotoAdapter(ArrayList<Foto> fotos, Activity activity) {
        this.fotos = fotos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_foto, parent, false);
        return new FotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        final Foto foto = fotos.get(position);
        Picasso.get().load(foto.getFotoURL()).into(holder.foto);
        holder.rating.setText(String.valueOf(foto.getRating()));
    }

    @Override
    public int getItemCount() {
        return this.fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView foto;
        private TextView rating;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);

            this.foto = (ImageView) itemView.findViewById(R.id.iv_foto_pic);
            this.rating = (TextView) itemView.findViewById(R.id.tv_rating_foto);
            //For simulating multiple photos, rotate and colorize randomly:
            //randomizePhoto();
        }

        private void randomizePhoto() {
            int[] angulos = {0, 90, 180, 270};
            int[] colores = {Color.BLACK, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW};

            Random rand = new Random();

            int randomColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            this.foto.setRotation(angulos[rand.nextInt(angulos.length)]);
            //this.foto.setColorFilter(colores[rand.nextInt(colores.length)], PorterDuff.Mode.LIGHTEN);
            this.foto.setColorFilter(randomColor, PorterDuff.Mode.LIGHTEN);
        }
    }
}
