package com.example.domilaap.Historial_Usuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.domilaap.R;
import com.example.domilaap.VisualizarProductos.carroCompra;
import com.example.domilaap.fragments.PedidosModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Historial_usuario extends AppCompatActivity {
    private RecyclerView recyclerViewPedido;
    private HistorialUsuarioAdapter pedidosAdapter;
    List<PedidosModelo> pedidosModelos;
    private String nombre_usu;
    private String URL;
    private ImageButton btnLimpiarHistorial;
    // LinearLayout lyLoading; ANIMACIONDE CARGA

  //  private TextView cantidadHistorial; // NUEVO
  //  int cantidad = 0; // NUEVO
    //private static String URL = "http://192.168.1.11/domilapp/menu/pedidos/listaPedidos.php";
    boolean pasar = false;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_usuario);
        carroCompra carro = new carroCompra();
        btnLimpiarHistorial = findViewById(R.id.btnLimpiarHistorial);
        // lyLoading = findViewById(R.id.lyLoading);
        recyclerViewPedido = (RecyclerView) findViewById(R.id.recicleHistorial);
        recyclerViewPedido.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPedido.setHasFixedSize(true);

       // cantidadHistorial = findViewById(R.id.cantidadHistorial); // NUEVO

        recyclerViewPedido.setAdapter(pedidosAdapter);
        pedidosModelos = new ArrayList<>();

        carro.imprimir2(); // carro; clase. carroCompra

        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false)
                .build();

        alertDialog.show();
        // lyLoading.setVisibility(View.VISIBLE);
        mostrarDatosUsuario();


        Mostrar();

        btnLimpiarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Borrar historial",Toast.LENGTH_SHORT).show();
            }
        });

/*
*
     if(recyclerViewPedido == null){
            alertDialog.dismiss();
            Toast.makeText(getApplication(), "Tu historail esta vacio.",Toast.LENGTH_SHORT).show();
        } else {

        } // if del NULL*/
     //   cantidadHistorial.setText(Integer.toString(cantidad)); // NUEVO

    }

    private void Mostrar(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                             //   cantidad++; // NUEVO
                                JSONObject player = array.getJSONObject(i);
                                pedidosModelos.add(new PedidosModelo(
                                        player.getString("productos"),
                                        player.getString("nombre_repartidor"), // nombre repartidor
                                        player.getString("nombre_usuario"), // nombre del usuario
                                        player.getString("telefono_repartidor"), // telefono repartidor
                                        player.getInt("cantidad"),// cantidad producto
                                        player.getString("precio_unidad"), //precio del producto
                                        player.getString("total"), // precio por cantidadplayer.getString("total") // precio por cantidad
                                        player.getString("direccion"), //direccion del pedido
                                        player.getString("img_producto")

                                        // canasta_familiar
                                ));
                            }
                            // lyLoading.setVisibility(View.GONE);
                            if (pedidosModelos.isEmpty()){
                                alertDialog.dismiss();
                                Toast.makeText(getApplication(), "Tu historail esta vacio.",Toast.LENGTH_SHORT).show();
                            } else {
                            alertDialog.dismiss();
                            Toast.makeText(getApplication(), "afuera 3",Toast.LENGTH_SHORT).show();
                            pedidosAdapter = new HistorialUsuarioAdapter(pedidosModelos);
                            recyclerViewPedido.setAdapter(pedidosAdapter);
                            //    RecyclerViewAdaptadorRepartidor recyclerViewAdaptadorRepartidor = new RecyclerViewAdaptadorRepartidor(Repartidor.this, repartidorModelos);
                            //      recyclerRepartidor.setAdapter(recyclerViewAdaptadorRepartidor);

                            } // IF DEL

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(), "Hay un error con el servidor",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void mostrarDatosUsuario(){  /// MUESTRA LA INFORMACION DEl USUARIO
        //   Guardar();
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasL", Context.MODE_PRIVATE);
        nombre_usu = sharedPreferences.getString("usu_nombre", "NO HAY DATO");
        // apellido_usu = sharedPreferences.getString("usu_apellido", "NO HAY DATO");
        // correo_usu = sharedPreferences.getString("usu_email", "NO HAY DATO");

        //nombreCompleto = nombre_usu + " " + apellido_usu;
        boolean sesion = sharedPreferences.getBoolean("sesion", true);
        if (sesion){
            //  nombre.setText(nombreCompleto);
            URL = "https://domilapp.000webhostapp.com/listaPedidos.php?nombre_usuario="+nombre_usu+"";
        }
    }
}