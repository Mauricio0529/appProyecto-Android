package com.example.domilaap.MetodoPago;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.domilaap.R;
import com.example.domilaap.factura.Factura;

public class MetodoPago extends AppCompatActivity {

    private Button btnMetodoPago;
    private String prnombre; // NOMBRE REPARTIDOR
    private String prTelefono; // TELEFONO DEL REPARTIDOR
    private int imagen; // IMAGEN PRODUCTO
    private RadioButton radio;
    private CardView cardCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);
        btnMetodoPago = findViewById(R.id.btnMetodoPago);
        radio = findViewById(R.id.radio);
        cardCheck = findViewById(R.id.cardCheck);

        prnombre = getIntent().getStringExtra("nombre"); // LLAGADA DE LA INFO DEL REPARTIDOR
        prTelefono = getIntent().getStringExtra("precio"); // TELEFONO REPARTIDOR
        //  imagen = getIntent().getIntExtra("imagen", 0);

       // btnMetodoPago.setEnabled(false);

        cardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio.setChecked(true);
               // btnMetodoPago.setEnabled(true);
            }
        });

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio.setChecked(true);
                //btnMetodoPago.setEnabled(true);
            }
        });

        btnMetodoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    private void check(){
        if (radio.isChecked() == true){
            Intent intent = new Intent(this, Factura.class); // Factura.class
            //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
            intent.putExtra("nombre", prnombre); // NOMBRE DEL REPARTIDOR
            intent.putExtra("precio", prTelefono); // TELEFONO DEL REPARTIDOR
            //  intent.putExtra("nombre", nombre);
            //   intent.putExtra("precio", precio); // HACER SHARED PREFERENCIA PARA ENVIAR LA INFO DEL PRODUCTO
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Debes seleccionar un metodo de pago", Toast.LENGTH_SHORT).show();
        }
    }
}