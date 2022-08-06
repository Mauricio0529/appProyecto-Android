package com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos;

public class ListaProductoModelo {
    private String nombreProductoCanasta;
    private int precioPC;
    private int fotoProducto;

    public ListaProductoModelo() {
    }

    public ListaProductoModelo(String nombreProductoCanasta, int precioPC, int fotoProducto) {
        this.nombreProductoCanasta = nombreProductoCanasta;
        this.precioPC = precioPC;
        this.fotoProducto = fotoProducto;
    }

    public String getNombreProductoCanasta() {
        return nombreProductoCanasta;
    }

    public void setNombreProductoCanasta(String nombreProductoCanasta) {
        this.nombreProductoCanasta = nombreProductoCanasta;
    }

    public int getPrecioPC() {
        return precioPC;
    }

    public void setPrecioPC(int precioPC) {
        this.precioPC = precioPC;
    }

    public int getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(int fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}
