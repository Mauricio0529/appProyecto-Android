package com.example.domilaap.Detalle_pedido;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.domilaap.R;
import com.example.domilaap.mensajeria.Chat;

public class DetallePedido extends AppCompatActivity {
    private TextView titulo;
    private TextView txtProducto, txtRepartidor, txtTelefono, txtCantidad, txtprecioCantidad, txtTotal;
    private ImageView img;
    private Button btnChat;
    private String nombreProducto, nombreRepartidor, telefono_repartidor, total, precio, img2, direccion;
    private int cantidad_productos;
    private ImageView fotoProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        titulo = findViewById(R.id.ts);
        txtProducto = findViewById(R.id.txtProducto);
        img = findViewById(R.id.img);
        txtRepartidor = findViewById(R.id.txtRepartidor);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtprecioCantidad = findViewById(R.id.txtprecioCantidad);
        txtTotal = findViewById(R.id.txtTotal);
        btnChat = findViewById(R.id.btnChat);

        // LLAGADA DE LA INFO DEL REPARTIDOR; VIENE DE PEDIDOS ADAPTER

        img2 = getIntent().getStringExtra("imagen"); // DA ERROR EN OCACIONES, TOCA OPTIMIZARLO

        nombreProducto = getIntent().getStringExtra("productos");
        nombreRepartidor = getIntent().getStringExtra("nombre");
        telefono_repartidor = getIntent().getStringExtra("telefono_repartidor");
        cantidad_productos = getIntent().getIntExtra("cantidad", 0);
        precio = getIntent().getStringExtra("precio");
        direccion = getIntent().getStringExtra("direccion");
        total = getIntent().getStringExtra("total");

        titulo.setText(direccion);

        MostrarProducto();
        MostrarRepartidor();

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetallePedido.this, Chat.class);
                startActivity(intent);
              //  Toast.makeText(getApplicationContext(), "CHAT", Toast.LENGTH_LONG).show();
            }
        });

    }
    private void MostrarProducto(){  /// PRODUCTO
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasP", Context.MODE_PRIVATE);
      //  String email = sharedPreferences.getString("resul", "NO HAY DATO");
        //String pass = sharedPreferences.getString("precio", "NO HAY DATO");
      //  String precioCantidad = sharedPreferences.getString("precioCantidad", "NO HAY DATO"); // precio por cantidad del producto
        //int cantidad = sharedPreferences.getInt("cantidadProducto", 0); // La cantidad del producto
       // int imagen = sharedPreferences.getInt("imagen", 0);
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            txtProducto.setText(nombreProducto); // nombre del producto
            txtCantidad.setText(Integer.toString(cantidad_productos)); // cantidad del producto
            txtprecioCantidad.setText(precio); // precio normal de un producto
            txtTotal.setText(total); // precio TOTAL

            img.setImageResource(Integer.parseInt(img2)); // ESTE ESTA MAL, DA ERROR EN ALGUNAS OCACIONES
        }
    }
    private void MostrarRepartidor(){  /// REPARTIDOR
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasR", Context.MODE_PRIVATE);
      //  String email = sharedPreferences.getString("nombre", "NO HAY DATO");
    //    String pass = sharedPreferences.getString("precio", "NO HAY DATO");
        boolean sesion = sharedPreferences.getBoolean("sesion", false);
        if (sesion){
            txtRepartidor.setText(nombreRepartidor);
            txtTelefono.setText(telefono_repartidor);
        }
    }
}