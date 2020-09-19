package com.example.w4118project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.w4118project.util.JavaMailUtil;
import com.google.android.material.textfield.TextInputEditText;

public class Contacto extends AppCompatActivity {

    private TextInputEditText teitNombre;
    private TextInputEditText teitEmail;
    private TextInputEditText teitComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.favoritos_actionbar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.teitNombre = (TextInputEditText) findViewById(R.id.tiet_nombre);
        this.teitEmail = (TextInputEditText) findViewById(R.id.tiet_email);
        this.teitComentario = (TextInputEditText) findViewById(R.id.tiet_comentario);
    }

    public void enviarComentario(View view) {
        String nombre = this.teitNombre.getText().toString();
        String email = this.teitEmail.getText().toString();
        String comentario = this.teitComentario.getText().toString();

        if (nombre.isEmpty() || email.isEmpty() || comentario.isEmpty()) {
            Toast.makeText(this, "Por favor llena todos los campos para continuar.", Toast.LENGTH_SHORT).show();
        }
        else {
            String subject = "Nuevo comentario de " + nombre;
            String body = "Nombre: " + nombre + "\n\n" +
                    "Email: " + email + "\n\n" +
                    "Comentario:\n\n" + comentario;
            String config_email = getResources().getString(R.string.EMAIL).toString();
            String config_password = getResources().getString(R.string.PASSWORD).toString();
            JavaMailUtil es = new JavaMailUtil(this, this, config_email, subject, body);
            es.setEMAIL(config_email);
            es.setPASSWORD(config_password);
            if (config_email.isEmpty() || config_password.isEmpty()) {
                Toast.makeText(this, "Credenciales de correo sin configurar en recurso strings.xml.", Toast.LENGTH_LONG).show();
            }
            else {
                es.execute();
            }
        }
    }
}