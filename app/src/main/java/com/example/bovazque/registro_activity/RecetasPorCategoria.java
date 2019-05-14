package com.example.bovazque.registro_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecetasPorCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
    }

    public void goToRecetasEncontradas(View view){
        Intent intent = new Intent(this, RecetasEncontradas_activity.class);
        intent.putExtra("busqueda", "categoria");
        intent.putExtra("categoria", getCategoriaFromId(view.getId()));
        startActivity(intent);
    }

    private String getCategoriaFromId(int id){
        switch (id){
            case R.id.carne_ib : return "carne";
            case R.id.pescado_ib : return "pescado";
            case R.id.verdura_ib : return "verdura";
            case R.id.pasta_ib : return "pasta";
            case R.id.arroz_ib : return "arroz";
            case R.id.postre_ib : return "postre";
            case R.id.legumbre_ib : return "legumbre";
            default : return "error";
        }
    }
}
