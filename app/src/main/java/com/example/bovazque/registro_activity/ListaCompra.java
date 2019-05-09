package com.example.bovazque.registro_activity;

import java.util.HashMap;
import java.util.Map;

public class ListaCompra {
    private Map<String, Integer> nombreMap;

    public ListaCompra(){
        this.nombreMap = new HashMap<String, Integer>();
    }

    public void anadirElemento(String nombre, int cantidad){
        this.nombreMap.put(nombre, cantidad);
    }

    public void eliminarElemento(String nombre){
        this.nombreMap.remove(nombre);
    }

    public HashMap getElementos(){
        return (HashMap) this.nombreMap;
    }

    public int getNumElementos(){
        return this.nombreMap.size();
    }

}