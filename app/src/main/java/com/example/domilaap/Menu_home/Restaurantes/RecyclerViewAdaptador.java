package com.example.domilaap.Menu_home.Restaurantes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.R;
import com.example.domilaap.VisualizarProductos.VerProductos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    private Context mCtx;
    private List<ProductoModelo> productoModelo;

    public RecyclerViewAdaptador(Context mCtx, List<ProductoModelo> productoModelo) {
        this.mCtx = mCtx;
        this.productoModelo = productoModelo;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        private  TextView precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreProducto);
            precio = (TextView) itemView.findViewById(R.id.tvPrecio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgRestaurante);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Context context = null;
                    Intent intent = new Intent(view.getContext(), VerProductos.class);
                    intent.putExtra("resul",  productoLista.get(getAdapterPosition()).getNombreProducto());
                    intent.putExtra("imagen", productoLista.get(getAdapterPosition()).getFotoProducto());
                    intent.putExtra("precio", productoLista.get(getAdapterPosition()).getPrecio());
                    view.getContext().startActivity(intent);
                   //view.getContext().startActivity(new Intent(view.getContext(), VerProductos.class).putExtra("resul", ProductoModelo.class));
                }
            });
        }
    }

    public List<ProductoModelo> productoLista;

    public RecyclerViewAdaptador(List<ProductoModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurantes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(productoLista.get(position).getFotoProducto())
                //.resize(60, 60)
                .into(holder.fotoProducto);

        holder.nombreProducto.setText(productoLista.get(position).getNombreProducto());
        holder.precio.setText(productoLista.get(position).getPrecio());
     //   holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}