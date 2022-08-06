package com.example.domilaap.factura;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.Menu;
import com.example.domilaap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factura extends AppCompatActivity {

    TextView titulo;
    private TextView txtNombreDom;
    private TextView txtTelefonoDom, txtTotal, txtDireccionPed;
    private Button btn_FinPedido, popup_window_button, btnCancelarPedido;
    private ImageView image;
    String nombre = "llega";
    String precio = "5";
    String prnombre;
    String proprecio;
    boolean pasar = false;
    String nombre_usu, apellido_usu, correo_usu, direccion;
    String nombreCompleto;
    int cantidad;
    String precioCantidad; // precio TOTAL
    Dialog dialog;
    private Button btnAceptar;
    private TextView tituloPositivo, textoPositivo, totalVentana;
    private ImageView cerrarPositivo;

    private static String URL = "https://domilapp.000webhostapp.com/listaCarritoCompraTemp.php";
    private RecyclerView recyclerView;
    private AdaptadorFactura adaptadorFactura;
    List<FacturaModelo> facturaModelo;

    String nombreProducto; // NOMBRE PRODUCTO
    int precioUnidadProducto; // PRECIO PRODUCTO
    int imagen; // IMAGEN PRODUCTO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        dialog = new Dialog(this);
        titulo = (TextView) findViewById(R.id.ts);
        btnCancelarPedido = findViewById(R.id.btnCancelarPedido);

        // TextView Repartdior
        txtNombreDom = findViewById(R.id.txtNombreDom);
        txtTelefonoDom = findViewById(R.id.txtTelefonoDom);

        // TextView Datos Pedido
        txtDireccionPed = findViewById(R.id.txtDireccionPed);

        btn_FinPedido = findViewById(R.id.btn_FinPedido);
            // txtNombrePro = findViewById(R.id.txtNombrePro);
        txtTotal = findViewById(R.id.txtTotal);

        image = findViewById(R.id.image);
        // txtprecioCantidad = findViewById(R.id.txtprecioCantidad);
        // txtCantidad = findViewById(R.id.txtCantidad);

        //titulo.setText("Confirmar Domiciliario");
        titulo.setText("Factura");

        prnombre = getIntent().getStringExtra("nombre"); // LLAGADA DE LA INFO DEL REPARTIDOR
        proprecio = getIntent().getStringExtra("precio");
        //  imagen = getIntent().getIntExtra("imagen", 0);


        recyclerView = (RecyclerView) findViewById(R.id.recicleFactura);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // recyclerView.setItemAnimator(new DefaultItemAnimator());

        facturaModelo = new ArrayList<>();

        if (nombre != null){  //   MOSTRAR REPARTIDOR REPARTIDOR
            txtNombreDom.setText(prnombre);
            txtTelefonoDom.setText(proprecio);
        }

        btn_FinPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarAccesoInternet();
            }
        });

        btnCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarPedido("https://domilapp.000webhostapp.com/borrarCarritoCompra.php");
            }
        });

        viewProductosDB();
        mostrarDatosUsuario();
        Mostrar();
    }

    private void viewProductosDB(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject producto = array.getJSONObject(i);
                                facturaModelo.add(new FacturaModelo(
                                        producto.getString("nombreProducto"),
                                        producto.getInt("precio_unidad"),
                                        producto.getString("img")
                                ));
                            }
                            //lyLoading.setVisibility(View.GONE);
                            adaptadorFactura = new AdaptadorFactura(facturaModelo);
                            recyclerView.setAdapter(adaptadorFactura);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ventanaDialog();
                        //Toast.makeText(getApplication(), "ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void Ventana(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Factura.this);
        alertDialog.setMessage("Estas seguro de realizar esta operacion?")
                .setCancelable(true)
                .setPositiveButton("Realizar Pedido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (pasar ==  false){
                            // si PASAR es falso; No me muestra la lista en el fragment Pedidos,
                            // ME MUESTRA EL TEXTO QUE DIGA NO HAY PEDIDOS EN CAMINO
                            GuardarBoolean();
                            pasar = true;
                        }
                        GuardarProducto(); // GUARDAMOS LA INFORMACION DEL PRODUCTO
                        registrarPedido("https://domilapp.000webhostapp.com/registroPedidos.php");
                        cancelarPedido("https://domilapp.000webhostapp.com/borrarCarritoCompra.php");
                        //registrarPedido("http://192.168.1.11/domilapp/menu/pedidos/registroPedidos.php");
                        ventanaDialog();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alertDialog.create();
        titulo.setTitle("Confirmar Pedido");
        titulo.show();
    }

    private void ventanaDialog() { // este es la nueva ventana (ES LA VERDE)
        dialog.setContentView(R.layout.epic_popup_positive); // se muestra todos los elementos de la ventana VERDE
        cerrarPositivo = (ImageView) dialog.findViewById(R.id.cerrarPositivo);
        btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        tituloPositivo = (TextView) dialog.findViewById(R.id.tituloPositivo);
        textoPositivo = (TextView) dialog.findViewById(R.id.textoPositivo);
        totalVentana = (TextView) dialog.findViewById(R.id.totalVentana);

        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
//        String pass = sharedPreferences.getString("precio", "NO HAY DATO"); //
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            totalVentana.setText("Total pedido: $" + precioCantidad);
        }

        cerrarPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    pasar = true;
                GuardarRepartidor();
                Intent intent = new Intent(Factura.this, Menu.class);
                intent.putExtra("nombre", nombre); // Se envia esta variable para ir al fragment PEDIDOS (Producto)
                intent.putExtra("precio", precio); // producto
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "exitoso!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         pasar = true;
                GuardarRepartidor();
                Intent intent = new Intent(Factura.this, Menu.class);
                intent.putExtra("nombre", nombre); // Se envia esta variable para ir al fragment PEDIDOS (Producto)
                intent.putExtra("precio", precio); // producto
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Su pedido fue exitoso asdasd", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Tu pedido viene en camino", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //  pasar = true;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

     /*   pasar = false;

        if (pasar == true) {


        } else {
            GuardarRepartidor();
            Intent intent = new Intent(Factura.this, Menu.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("precio", precio);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Su pedido fue exitoso No ventana!", Toast.LENGTH_LONG).show();
        }*/

    }

    private void Mostrar(){  /// MUESTRA LA INFORMACION DEL PRODUCTO, ESTA VIENE DE verProductos
        //   Guardar();
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
        nombreProducto = sharedPreferences.getString("resul", "No hay dato"); // nombre del producto
        precioUnidadProducto = sharedPreferences.getInt("precio", 0); // precio unitario del producto
        // String.valueOf(precioUnidadProducto)
        direccion = sharedPreferences.getString("direccion", "No hay dato"); // direccion usuario del pedido
        precioCantidad = sharedPreferences.getString("precioCantidad", "No hay dato"); // precio por cantidad del producto
        cantidad = sharedPreferences.getInt("cantidadProducto", 0); // La cantidad del producto

        imagen = sharedPreferences.getInt("imagen", 0); // imagen del producto
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            // txtNombrePro.setText(nombreProducto); // NOMBRE DEL PRODUCTO
            txtTotal.setText(precioCantidad); // PRECIO total DEL PRODUCTO
            txtDireccionPed.setText(direccion); // MOSTRANDO LA DIRECCION DEL PEDIDO
            // txtprecioCantidad.setText(Integer.toString(precioUnidadProducto)); // PRECIO UNITARIO DEL PRODUCTO
            // txtCantidad.setText(Integer.toString(cantidad)); // PRECIO DEL PRODUCTO

            // image.setImageResource(imagen); // IMAGEN DEL PRODUCTO
        }
    }

    private void mostrarDatosUsuario(){  /// MUESTRA LA INFORMACION DEl USUARIO
        //   Guardar();
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        nombre_usu = sharedPreferences.getString("usu_nombre", "NO HAY DATO");
        apellido_usu = sharedPreferences.getString("usu_apellido", "NO HAY DATO");
        correo_usu = sharedPreferences.getString("usu_email", "NO HAY DATO");

        nombreCompleto = nombre_usu + " " + apellido_usu;
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
    }

    /*
        REGISTRO DEL PEDIDO
    */
    private void registrarPedido(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                ventanaDialog2();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre_usuario", nombre_usu); // nombre usuario
                parametros.put("correo_usuario", correo_usu); // correo usuario
                parametros.put("nombre_repartidor", prnombre); // nombre repartidor
                parametros.put("telefono_repartidor", proprecio); // telefono repartidor
                parametros.put("productos", nombreProducto); // nombre producto
                parametros.put("precio_unidad", String.valueOf(precioUnidadProducto)); // precio unidad producto
                //parametros.put("precio_cantidad", pass); // precio cantidad producto
                //parametros.put("precio_total", pass); // precio total del pedido
                parametros.put("cantidad", String.valueOf(cantidad)); // cantidad producto
                parametros.put("total", precioCantidad); // total del pedido
                parametros.put("direccion", direccion); // direccion pedido

                // TOCA MEJORAR EL CODIGO DE FOTO PRODUCTO; PARA PEDIDOS ADAPTER Y DETALLE PEDIDO
                parametros.put("img_producto", String.valueOf(imagen)); // imagen producto ESTE NO, MEJOR EL SHAREDPREFERENCIAS

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /* CANCELAR PEDIDO */
    private void cancelarPedido(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    boolean succes = object.getBoolean("success");
                    if(succes){
                        Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "HUBO UN ERROR!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                ventanaDialog2();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void verificarAccesoInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            Ventana();
            //  Toast.makeText(MainActivity.this, "Si hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        } else {
            // No hay conexión a Internet en este momento
            // Ventana();
            ventanaDialog2();
            //Toast.makeText(MainActivity.this, "No hay conexión a Internet en este momento", Toast.LENGTH_SHORT).show();
        }
    }

    private void ventanaDialog2() { // este es la nueva ventana (ES LA ROJA DEL INTERNET)
        dialog.setContentView(R.layout.pop_internet); // se muestra todos los elementos de la ventana de INTERNET

        popup_window_button = (Button) dialog.findViewById(R.id.popup_window_button);
        // popup_window_title = (TextView) dialog.findViewById(R.id.popup_window_title);
        // popup_window_text = (TextView) dialog.findViewById(R.id.popup_window_text);


        popup_window_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //      dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void GuardarRepartidor(){   // REPARTIDOR
        SharedPreferences preferences = getSharedPreferences("preferenciasR", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", prnombre);   // 	nombre repartidor
        editor.putString("precio", proprecio);  // telefono repartidor
        editor.putBoolean("sesion", true);
        editor.commit();

    }
    // ESTE NO SIRVE PARA NADA, ESTE METODO ES CLONADO
    private void GuardarProducto(){ // GUARDAR LA INFORMACION DEL PRODCUTO PARA MOSTRARLA EN LA ACTIVITY PEDIDOS
        SharedPreferences preferences = getSharedPreferences("preferenciasP2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("resul2", nombreProducto);   // 	NOMBRE DEL PRODUCTO
        editor.putString("precio2", String.valueOf(precioUnidadProducto));  // PRECIO DEL PRODUCTO
        editor.putInt("imagen2", imagen);  // IMAGEN DEL PRODUCTO
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void GuardarBoolean(){   // Dato para visualizar la lista de PEDIDOS en el Fragment PEDIDOS
        SharedPreferences preferences = getSharedPreferences("preferenciasDT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dat", pasar);   // nombre repartidor
        editor.putBoolean("sesion", true);
        editor.commit();

    }
}