package com.example.domilaap.Menu_home.AlmacenesCadena.Categorias;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.VisualizarProductos.VerProductos;
import com.example.domilaap.R;

import java.util.List;

public class RecyclerViewAdaptadorAC extends RecyclerView.Adapter<RecyclerViewAdaptadorAC.ViewHolder>  {

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        private  TextView precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), VerProductos.class);
                    //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
                    //  intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto());
                    view.getContext().startActivity(intent);
                }
            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreAlmacenCategoria);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgCategoria);
        }
    }

    public List<CategoriaModelo> productoLista;

    public RecyclerViewAdaptadorAC(List<CategoriaModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptadorAC.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_almacenes_categoria, parent, false);
        RecyclerViewAdaptadorAC.ViewHolder viewHolder = new RecyclerViewAdaptadorAC.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorAC.ViewHolder holder, int position) {
        holder.nombreProducto.setText(productoLista.get(position).getNombreCategoria());
        holder.precio.setText(productoLista.get(position).getPrecio());
        holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}
