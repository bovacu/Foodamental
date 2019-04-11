package com.example.bovazque.registro_activity;

public class Usuarios {
    private String claseid;
    private String nombre;
    private String apellido;
    private String pais;
    private String telefono;

    public Usuarios(String claseid, String nombre, String apellido, String pais, String telefono) {

        this.claseid = claseid;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.telefono = telefono;
    }

    public String getClaseid() {
        return claseid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPais() {
        return pais;
    }

    public String getTelefono() {
        return telefono;
    }
}
