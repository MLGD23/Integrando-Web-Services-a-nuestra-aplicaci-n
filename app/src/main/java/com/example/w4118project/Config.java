package com.example.w4118project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.w4118project.restAPI.ConstantesRestAPI;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Config extends AppCompatActivity {

    public static final String NOMBRE_ARCHIVO = "ig_user_id";

    private TextInputEditText teitNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.favoritos_actionbar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.teitNombreUsuario = (TextInputEditText) findViewById(R.id.tiet_nombre_usuario);

        if (!ConstantesRestAPI.USER_ID.isEmpty()) {
            this.teitNombreUsuario.setText(ConstantesRestAPI.USER_ID);
        }
    }

    public void guardarCuenta(View view) {
        Toast.makeText(this, "Guardando Cuenta", Toast.LENGTH_SHORT).show();
        String userID = this.teitNombreUsuario.getText().toString();
        escribirCuenta(userID);
        ConstantesRestAPI.USER_ID = userID;
    }

    public void escribirCuenta(String texto) {
        try {
            FileOutputStream fos = openFileOutput(Config.NOMBRE_ARCHIVO, Context.MODE_PRIVATE);
            fos.write(texto.getBytes());
            fos.close();
        }
        catch (Exception e) {
            Log.e("TAG", "[Config.escribirCuenta()] ERROR: " + e.getMessage());
            Toast.makeText(this, "ERROR Guardando Cuenta", Toast.LENGTH_SHORT).show();
        }
    }
}