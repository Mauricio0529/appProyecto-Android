package com.example.domilaap.Menu_home.AlmacenesCadena.Categorias;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;

import java.util.ArrayList;
import java.util.List;

public class Almacen_categorias extends AppCompatActivity {

    private RecyclerView recyclerViewCategoria;
    private RecyclerViewAdaptadorAC adaptadorCategoria;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_categorias);

// https://www.youtube.com/watch?v=Uur6-64KqxI
        recyclerViewCategoria = (RecyclerView) findViewById(R.id.recicleCategoria);
        recyclerViewCategoria.setLayoutManager(new LinearLayoutManager(this));
        titulo = findViewById(R.id.ts);

        titulo.setText("Categoria");
        adaptadorCategoria = new RecyclerViewAdaptadorAC(obtenerCategoria());
        recyclerViewCategoria.setAdapter(adaptadorCategoria);
    }

    public List<CategoriaModelo> obtenerCategoria(){
        List<CategoriaModelo> producto = new ArrayList<>();
        producto.add(new CategoriaModelo("Canasta Familiar", "Mercar", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Vinos y Licores", "Bebidas alcoholicas", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Bebidas Gaseosas", "Refrescos", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Enlatados y conseervas", "Alimentos envasados", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Despensa", "Alimentos regulares", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Lacteos", "Lacteos", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Pollo, carnes y pescado", "Tipos de carnes", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Frutas y Verduras", "Comida Saludable", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Aseo del hogar", "Limpieza para su hogar", R.drawable.ic_launcher_background));
        producto.add(new CategoriaModelo("Aseo personal", "Limpieza personal", R.drawable.ic_launcher_background));

        return producto;
    }
}
