package com.example.domilaap.fragments;

public class MenuModelo {
    String nombre;
    private String fotoProducto;

    public MenuModelo() {
    }

    public MenuModelo(String nombre, String fotoProducto) {
        this.nombre = nombre;
        this.fotoProducto = fotoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}
