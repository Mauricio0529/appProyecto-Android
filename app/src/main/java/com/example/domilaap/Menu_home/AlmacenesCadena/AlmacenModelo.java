package com.example.domilaap.Menu_home.AlmacenesCadena;

public class AlmacenModelo {
  //  private String idAlmacen;
    private String nombreProducto;
    private String precio;
    private String fotoProducto;

    public AlmacenModelo() {
    }

    public AlmacenModelo(String nombreProducto, String precio, String fotoProducto) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.fotoProducto = fotoProducto;
    }

  /*  public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }*/

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
