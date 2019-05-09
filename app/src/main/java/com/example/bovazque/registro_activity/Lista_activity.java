package com.example.bovazque.registro_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Lista_activity extends AppCompatActivity implements AnadirAlimentoDialog.AnadirAlimentoDialogListener {

    private ListaCompra lista;
    private ScrollView panel_alimentos;
    private LinearLayout linearLayout;
    private Button anadir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_activity);

        this.panel_alimentos = (ScrollView) findViewById(R.id.panel_alimentos);
        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        this.anadir = (Button) findViewById(R.id.anadir_button);
        this.anadir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        this.lista = new ListaCompra();
        this.lista.anadirElemento("Patatas", 2);
        this.lista.anadirElemento("Arroz", 3);
        mostrarElementos();

    }

    public void mostrarElementos(){
            this.linearLayout.removeAllViews();
            for(Object key : this.lista.getElementos().keySet()){
                String nombre = (String) key;
                Button button = new Button(this);
                button.setText(nombre);
                linearLayout.addView(button);
            }
    }

    public void Onclick(){

    }

    public void openDialog(){
        AnadirAlimentoDialog dialog = new AnadirAlimentoDialog();
        dialog.show(getSupportFragmentManager(), "");


    }


    @Override
    public void applyTexts(String nombreAlimento, int cantidad) {
        this.lista.anadirElemento(nombreAlimento, cantidad);
        mostrarElementos();
    }
}
