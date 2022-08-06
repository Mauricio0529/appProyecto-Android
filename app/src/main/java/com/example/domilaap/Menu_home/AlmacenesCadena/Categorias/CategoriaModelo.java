package com.example.domilaap.Menu_home.AlmacenesCadena.Categorias;

public class CategoriaModelo {
    private String idCategoria;
    private String nombreCategoriaa;
    private String precio;
    private int fotoProducto;

    public CategoriaModelo() {
    }

    public CategoriaModelo(String nombreCategoriaa, String precio, int fotoProducto) {
        this.nombreCategoriaa = nombreCategoriaa;
        this.precio = precio;
        this.fotoProducto = fotoProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoriaa;
    }

    public void setNombreCategoria(String nombreCategoriaa) {
        this.nombreCategoriaa = nombreCategoriaa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(int fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}
