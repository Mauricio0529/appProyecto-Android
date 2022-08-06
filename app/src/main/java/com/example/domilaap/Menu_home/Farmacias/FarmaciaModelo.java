package com.example.domilaap.Menu_home.Farmacias;

public class FarmaciaModelo {
    private String nombreFarmacia;
    private String precio;
    private String fotoFarmacia;


    public FarmaciaModelo() {
    }

    public FarmaciaModelo(String nombreFarmacia, String precio,  String fotoFarmacia) {
        this.nombreFarmacia = nombreFarmacia;
        this.precio = precio;
        this.fotoFarmacia = fotoFarmacia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombreFarmacia() {
        return nombreFarmacia;
    }

    public void setNombreFarmacia(String nombreFarmacia) {
        this.nombreFarmacia = nombreFarmacia;
    }

    public String getFotoFarmacia() {
        return fotoFarmacia;
    }

    public void setFotoFarmacia(String fotoFarmacia) {
        this.fotoFarmacia = fotoFarmacia;
    }
}