package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_activity);
    }


    public void goToRecetas(View view) {
        Intent intencion = new Intent(getApplication(), TipoBusquedaReceta.class);
        startActivity(intencion);
    }
}
