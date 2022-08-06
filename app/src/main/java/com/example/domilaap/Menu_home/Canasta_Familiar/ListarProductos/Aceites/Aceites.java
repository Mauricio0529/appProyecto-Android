package com.example.domilaap.Menu_home.Canasta_Familiar.ListarProductos.Aceites;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;

import java.util.ArrayList;
import java.util.List;

public class Aceites extends AppCompatActivity {
    private TextView titulo;
    private RecyclerView recyclerViewLPC;
    private RecyclerViewAdaptadorCanastaFA adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceites);

        titulo = findViewById(R.id.ts);
        recyclerViewLPC = (RecyclerView) findViewById(R.id.recicleAceite);
        recyclerViewLPC.setLayoutManager(new GridLayoutManager(this, 2));
        adaptador = new RecyclerViewAdaptadorCanastaFA(obtenerListaProducto());
        recyclerViewLPC.setAdapter(adaptador);
        titulo.setText("Aceites");
    }

    public List<AceiteModelo> obtenerListaProducto(){
        List<AceiteModelo> producto = new ArrayList<>();
        producto.add(new AceiteModelo("Aceite GOURMET girasol familia 2000 ml", 25100, R.drawable.aceite1));
        producto.add(new AceiteModelo("Aceite Vegetal oliosoya 3000 ml", 21300, R.drawable.aceite_vegetal_olisoya3000ml));
        producto.add(new AceiteModelo("Aceite PREMIER Girasol", 30150, R.drawable.aceite_girasol3000ml));
        producto.add(new AceiteModelo("Aceite oliva TAEQ extravirgen 500 ml", 13850, R.drawable.aceite_oliva_taeq));
        producto.add(new AceiteModelo("Aceite Girasol frescampo 3000 ml", 23400, R.drawable.aceite_girasol_fresco3000));
        producto.add(new AceiteModelo("Aceite Girasol frescampo 900 ml", 1800, R.drawable.aceite_girasol_fresco900));
        producto.add(new AceiteModelo("Aceite Vegetal Riquisimo 1000 ml", 6300, R.drawable.aceite_riquisimo1000));
        producto.add(new AceiteModelo("Aceite vegetal diana con vitaminas 3000 ml", 18700, R.drawable.aceite_diana));
        producto.add(new AceiteModelo("Aceite Olisoya 900 ml", 6550, R.drawable.aceite_oliosoya900ml));
        producto.add(new AceiteModelo("Aceite Girasol premier light 3000 ml", 35300, R.drawable.aceite_premier_girasol3000));
        producto.add(new AceiteModelo("Aceite de grasas trans riquisimo 3000 ml", 18800, R.drawable.aceite_riquisimo3000));

        return producto;
    }
}// AceiteModelo