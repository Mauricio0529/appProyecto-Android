package com.example.domilaap.VisualizarProductos;

public class carritoProducto {
    String nombreProducto;
    int precio;
    String fotoProducto; // string


    public carritoProducto(String nombreProducto, int precio, String fotoProducto) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.fotoProducto = fotoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }
    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}