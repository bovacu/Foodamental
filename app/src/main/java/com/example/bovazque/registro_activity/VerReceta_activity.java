package com.example.bovazque.registro_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
                DataSnapshot tiempo = dataSnapshot.child(getIntent().getStringExtra("nombreReceta")).child("Tiempo");

                for (DataSnapshot ingrediente : ingredientesReceta.getChildren()) {
                    anadirIngredienteALaLista(ingrediente.getKey() + " : " + ingrediente.getValue());
                }

                if(tiempo.getValue() != null) {
                    TextView tv = findViewById(R.id.tiempo_tv);
                    tv.setText(tiempo.getValue().toString());
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

    private int getImagenReceta(String nombreReceta) {
        nombreReceta = nombreReceta.toLowerCase().replaceAll("\\s+", "").trim();
        switch(nombreReceta){
            case "arrozalacubana" : return R.drawable.arrozalacubana;
            case "arrozconpollo" : return R.drawable.arrozconpollo;
            case "caipiroshkademaracuya" : return R.drawable.caipiroshkademaracuya;
            case "esqueixadadebacalao" : return R.drawable.esqueixadadebacalao;
            case "fresasconnata" : return R.drawable.fresasconnata;
            case "garbanzosconverduras" : return R.drawable.garbanzosconverduras;
            case "huevocate" : return R.drawable.huevocate;
            case "lubinaalhorno" : return R.drawable.lubinaalhorno;
            case "medialunasconquesoysetas" : return R.drawable.medialunasconquesoysetas;
            case "tartadequeso" : return R.drawable.tartadequeso;
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