package com.example.bovazque.registro_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class VerReceta_activity extends AppCompatActivity {

    private List<String> listaIngredientes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verreceta_activity);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recetas");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ListView listView = findViewById(R.id.listaIngredientes_lv);

                listaIngredientes = new ArrayList<>();
                adapter = new ArrayAdapter<>(VerReceta_activity.this, android.R.layout.simple_list_item_1, listaIngredientes);
                listView.setAdapter(adapter);

                DataSnapshot ingredientesReceta = dataSnapshot.child(getIntent().getStringExtra("nombreReceta")).child("Ingredientes");

                for (DataSnapshot ingrediente : ingredientesReceta.getChildren()) {
                    anadirIngredienteALaLista(ingrediente.getKey() + " : " + ingrediente.getValue());
                }

                ImageView imagen = findViewById(R.id.imagenReceta);
                imagen.setImageResource(getImagenReceta(getIntent().getStringExtra("nombreReceta")));

                findViewById(R.id.progressBar2).setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getImagenReceta(String nombreReceta){
        nombreReceta = nombreReceta.toLowerCase().replaceAll("\\s+", "").trim();
        switch(nombreReceta){
            case "arrozalacubana" : return R.drawable.arrozalacubana;
            default : return -1;
        }
    }

    private void anadirIngredienteALaLista(String ingrediente) {
        if(!this.listaIngredientes.contains(ingrediente) && ingrediente.trim().length() > 0) {
            this.listaIngredientes.add(ingrediente);
            this.adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "El alimento no puede estar vacio", Toast.LENGTH_SHORT).show();
        }
    }


}