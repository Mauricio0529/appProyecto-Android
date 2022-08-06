package com.example.domilaap.VisualizarProductos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorCarroCompras extends RecyclerView.Adapter<AdaptadorCarroCompras.ViewHolder> {

    Context mCtx;
    List<carritoProducto> carritoLista;
    TextView txtValorTotalProductos;
    int vtotal = 0;

     public AdaptadorCarroCompras(Context mCtx, List<carritoProducto> carritoLista, TextView txtValorTotalProductos) {
        this.mCtx = mCtx;
        this.carritoLista = carritoLista;
        this.txtValorTotalProductos = txtValorTotalProductos;
        for (int i = 0; i < carritoLista.size(); i++){
           //txtValorTotalProductos.setText(carritoLista.get(i).getNombreProducto());
             vtotal = vtotal + Integer.parseInt("" + carritoLista.get(i).getPrecio());

           // vtotal = vtotal +  carritoLista.get(i).getPrecio();
        }
         txtValorTotalProductos.setText(""+vtotal);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carritocompra, viewGroup, false);
        AdaptadorCarroCompras.ViewHolder viewHolder = new AdaptadorCarroCompras.ViewHolder(view);
        return viewHolder;
        //return null;
    }


   // public List<carritoProducto> carritoLista;

    public AdaptadorCarroCompras(List<carritoProducto> carritoLista) {
        this.carritoLista = carritoLista;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarroCompras.ViewHolder viewHolder, final int i) {
        viewHolder.nombreProducto.setText(carritoLista.get(i).getNombreProducto());
       // viewHolder.fotoProducto.setImageResource(carritoLista.get(i).getFotoProducto());

        Picasso.get().load(carritoLista.get(i).getFotoProducto())
                //.resize(60, 60)
                .into(viewHolder.fotoProducto);


        viewHolder.precioProducto.setText(""+carritoLista.get(i).getPrecio()); // pasar int a String ("")
    }

    @Override
    public int getItemCount() {
        return carritoLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProducto, precioProducto;
        ImageView fotoProducto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            fotoProducto = itemView.findViewById(R.id.fotoProducto);
            precioProducto = itemView.findViewById(R.id.precioProducto);
        }
    }
}