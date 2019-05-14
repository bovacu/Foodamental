package com.example.bovazque.registro_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.CheckBox;

public class Lista_activity extends AppCompatActivity implements AnadirAlimentoDialog.AnadirAlimentoDialogListener {

    private static ListaCompra lista;
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
                LinearLayout myLinearLayout = new LinearLayout(this);
                myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                String nombre = (String) key;
                Button button = new Button(this);
                LinearLayout.LayoutParams eqparams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                button.setLayoutParams(eqparams);
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        ViewGroup parentButton = (ViewGroup) v.getParent();
                        ViewGroup parentLinearLayout = (ViewGroup) parentButton.getParent();
                        Button b = (Button) v;
                        lista.eliminarElemento(b.getText().toString());
                        parentLinearLayout.removeView(parentButton);

                        //parentView.removeView(v);
                        //System.out.println(b.getText().toString());

                        return true;
                    }
                });
                button.setText(nombre);
                CheckBox checkBox = new CheckBox(this);
                myLinearLayout.addView(checkBox);
                myLinearLayout.addView(button);
                linearLayout.addView(myLinearLayout);
            }
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
