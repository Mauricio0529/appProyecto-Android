package com.example.domilaap.Listar_repartidor;

public class RepartidorModelo {
    private String nombreProducto;  // nombre del repartidor
    private String precio;  //
    /*private String nombre2;
    private String precio2;
    private int imagen2; */

    //  private int fotoProducto;


    public RepartidorModelo() {
    }

    public RepartidorModelo(String nombreProducto, String precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
  /*      this.nombre2 = nombre2;
        this.precio2 = precio2;
        this.imagen2 = imagen2; */
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
}
