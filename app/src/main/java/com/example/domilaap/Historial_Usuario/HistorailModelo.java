package com.example.domilaap.Historial_Usuario;

public class HistorailModelo {

    private String producto;
    private String nombre; // nombre del repartidor
    private String nombre_usuario; // este se agrego
    private String telefono;
    private String precio;
    private String total;
    private int cantidad;
    private String direccion;
    private String fotoProducto;

    public HistorailModelo() {
    }

    public HistorailModelo(String producto, String nombre, String nombre_usuario, String telefono, String precio, String total, int cantidad, String direccion, String fotoProducto) {
        this.producto = producto;
        this.nombre = nombre;
        this.nombre_usuario = nombre_usuario;
        this.telefono = telefono;
        this.precio = precio;
        this.total = total;
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.fotoProducto = fotoProducto;
    }


    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}