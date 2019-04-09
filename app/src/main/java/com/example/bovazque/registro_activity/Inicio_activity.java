package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicio_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_activity);

    }

    public void goToInicioSesion(View view) {
        Intent intent = new Intent(this, InicioSesion_activity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void goToRegistro(View view) {
        Intent intent = new Intent(this, Registro_activity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
