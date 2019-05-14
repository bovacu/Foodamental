package com.example.bovazque.registro_activity;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RecetasEncontradas_activity extends AppCompatActivity {

    private List<String> listaRecetasFirebase;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_encontradas_activity);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recetas");

        final Context context = this;

        final ListView recetas_listView = findViewById(R.id.recetasEncontradas_list);

        recetas_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreReceta = adapter.getItem(position);

                Intent intencion = new Intent(RecetasEncontradas_activity.this, VerReceta_activity.class);
                intencion.putExtra("nombreReceta", nombreReceta);
                startActivity(intencion);
            }
        });

        this.listaRecetasFirebase = new ArrayList<>();
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.listaRecetasFirebase);
        recetas_listView.setAdapter(adapter);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String tipoBusqueda = getIntent().getStringExtra("busqueda");

                    if(tipoBusqueda.equals("ingredientes")){
                        List<String> ingredientesABuscar = new ArrayList<>();
                        for(String ingr : (List<String>) getIntent().getSerializableExtra("listaIngredientes")){
                            ingredientesABuscar.add(ingr.toLowerCase().replaceAll("\\s+", ""));
                        }

                        buscarPorIngredientes(recetas_listView, ingredientesABuscar, dataSnapshot, context);

                    } else if(tipoBusqueda.equals("categoria")){
                        buscarPorCategoria(recetas_listView, getIntent().getStringExtra("categoria").toLowerCase().replaceAll("\\s+", ""), dataSnapshot, context);
                    } else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        private void buscarPorCategoria(ListView recetas_listView, String categoria, DataSnapshot dataSnapshot, Context context){

            categoria = categoria.toLowerCase().replaceAll("\\s+", "");
            for (DataSnapshot receta : dataSnapshot.getChildren()) {
                DataSnapshot cat = receta.child("Categoria");

                if(cat.getValue() != null){
                    String[] categorias = ((String)cat.getValue()).split(",");

                    for(String c : categorias){
                        System.out.println(c + " ----> " + categoria);
                        if(c.toLowerCase().replaceAll("\\s+", "").equals(categoria)) {
                            if(!yaEnLosResultados(recetas_listView, receta.getKey())){
                                this.anadirIngredienteALaLista(receta.getKey());
                            }
                        }
                    }
                }
            }
        }

        private void buscarPorIngredientes(ListView recetas_listView, List<String> ingredientesABuscar, DataSnapshot dataSnapshot, Context context){
            for (DataSnapshot receta : dataSnapshot.getChildren()) {
                DataSnapshot ingredientes = receta.child("Ingredientes");

                List<String> ingredientesReceta = new ArrayList<>();

                for (DataSnapshot ingrediente : ingredientes.getChildren())
                    ingredientesReceta.add(ingrediente.getKey().toLowerCase().replaceAll("\\s+", ""));

                if (ingredientesReceta.containsAll(ingredientesABuscar) ) {
                    if(!yaEnLosResultados(recetas_listView, receta.getKey())){
                        this.anadirIngredienteALaLista(receta.getKey());
                    }
                }
            }
        }

        private boolean yaEnLosResultados(ListView recetas_listView, String receta){
            for(int i = 0; i < recetas_listView.getChildCount(); i++){
                if(recetas_listView.getChildAt(i) instanceof Button) {
                    if (((Button)recetas_listView.getChildAt(i)).getText().equals(receta))
                        return true;
                }
            }
            return false;
        }

        private void anadirIngredienteALaLista(String ingrediente) {
            if(!this.listaRecetasFirebase.contains(ingrediente) && ingrediente.trim().length() > 0) {
                this.listaRecetasFirebase.add(ingrediente);
                this.adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "El alimento no puede estar vacio", Toast.LENGTH_SHORT).show();
            }
        }
    }
