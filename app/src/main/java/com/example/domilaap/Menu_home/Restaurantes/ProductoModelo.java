package com.example.domilaap.Menu_home.Restaurantes;

public class ProductoModelo{
    private String nombreProducto;
    private String precio;
    private String fotoProducto;

    public ProductoModelo() {
    }

    public ProductoModelo(String nombreProducto, String precio, String fotoProducto) {
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

}