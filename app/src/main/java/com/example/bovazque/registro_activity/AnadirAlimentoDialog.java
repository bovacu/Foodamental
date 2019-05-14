package com.example.bovazque.registro_activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AnadirAlimentoDialog extends AppCompatDialogFragment {
    private EditText anadirAlimento;
    //private EditText cantidad;
    private AnadirAlimentoDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final Context context = super.getContext();
        View view = inflater.inflate(R.layout.anadir_lista_compra_activity, null);

        builder.setView(view)
                .setTitle("Anadir Alimento")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreAli = anadirAlimento.getText().toString();
                        //String cant = cantidad.getText().toString();
                        int ca = 0;
                        if(!nombreAli.equals("")){
                            /*if(!cant.equals("")){
                                ca = Integer.valueOf(cant);
                            }*/
                            listener.applyTexts(nombreAli, ca);
                        }else{
                            Toast.makeText(context, "Nombre articulo no puede estar vacío", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        anadirAlimento = view.findViewById(R.id.nombreAli);
        //cantidad = view.findViewById(R.id.cantidad);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (AnadirAlimentoDialogListener) context;
        }catch(ClassCastException e){
           throw new ClassCastException(context.toString()+ "must implement AnadirAlimentoDialogListener");
        }
    }

    public interface AnadirAlimentoDialogListener{
        void applyTexts(String nombreAlimento, int cantidad);
    }

}
