package com.example.bovazque.registro_activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecetasPorCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        ListView listView = findViewById(R.id.lista_categorias);

        final Application app = getApplication();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(app, RecetasEncontradas_activity.class);
                intent.putExtra("busqueda", "categoria");
                intent.putExtra("categoria", parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }

    public void goToRecetasEncontradas(View view) {
        Intent intencion = new Intent(getApplication(), RecetasEncontradas_activity.class);
        intencion.putExtra("listaIngredientes", "");
        intencion.putExtra("busqueda", "categoria");
        startActivity(intencion);
    }
}
