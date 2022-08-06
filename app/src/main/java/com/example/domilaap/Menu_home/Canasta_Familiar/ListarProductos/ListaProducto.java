package com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;

import java.util.ArrayList;
import java.util.List;

public class ListaProducto extends AppCompatActivity {
    private TextView titulo;
    private RecyclerView recyclerViewLPC;
    private RecyclerViewAdaptadorLPC adaptadorLPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);

        // LO CONECTAMOS CON EL RECYCLERVIEW DEL LAYOUT
        titulo = findViewById(R.id.ts);
        recyclerViewLPC = (RecyclerView) findViewById(R.id.recicleProductosCanasta);
        recyclerViewLPC.setLayoutManager(new GridLayoutManager(this, 2));
        //recyclerViewLPC.setLayoutManager(new LinearLayoutManager(this));

        adaptadorLPC = new RecyclerViewAdaptadorLPC(obtenerListaProducto());
        recyclerViewLPC.setAdapter(adaptadorLPC);
        titulo.setText("Arrozes y abarrotes");
    }

    public List<ListaProductoModelo> obtenerListaProducto(){
        List<ListaProductoModelo> producto = new ArrayList<>();
        producto.add(new ListaProductoModelo("Arroz Roa 1 kilo", 3350, R.drawable.roa_1kilo));
        producto.add(new ListaProductoModelo("Arroz Diana 500 gr", 1700, R.drawable.diana500g));
        producto.add(new ListaProductoModelo("Arroz Fortificado Roa 5000g", 18550, R.drawable.roa_fortificado));
        producto.add(new ListaProductoModelo("Arroz Diana Premium 4000 Kg", 20550, R.drawable.diana_premium_4000kg));
        producto.add(new ListaProductoModelo("Arroz Flor Huila 3000 gr", 11800, R.drawable.florhuila_3000g));
        producto.add(new ListaProductoModelo("Arroz Roa 500 gr", 1800, R.drawable.roa500g));
        producto.add(new ListaProductoModelo("Arroz Sonora 3000 gr", 11200, R.drawable.arroz_sonora));
        producto.add(new ListaProductoModelo("Arroz Flor Huila 1000 gr", 3900, R.drawable.florhuila_1000kg));

        return producto;
    }



}
