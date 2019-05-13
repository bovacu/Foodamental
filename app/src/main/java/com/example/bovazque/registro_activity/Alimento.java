package com.example.bovazque.registro_activity;

class Alimento {
    private String nombrealimento;
    private int cantidad;
    public Alimento(String nombre, int cantidad){
        this.nombrealimento=nombre;
        this.cantidad = cantidad;
    }
    public String getNombrealimento() {
        return nombrealimento;
    }

    public void setNombrealimento(String nombrealimento) {
        this.nombrealimento = nombrealimento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
