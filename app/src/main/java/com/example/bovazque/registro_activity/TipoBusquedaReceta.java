package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TipoBusquedaReceta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_busqueda_receta);
    }

    public void goToPorIngredientes(View view) {
        Intent intencion = new Intent(getApplication(), RecetasPorIngredientes.class);
        startActivity(intencion);
    }

    public void goToPorCategoria(View view) {
        Intent intencion = new Intent(getApplication(), RecetasPorCategoria.class);
        startActivity(intencion);
    }
}
