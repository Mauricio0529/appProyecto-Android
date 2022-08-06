package com.example.domilaap.Menu_home.AlmacenesCadena;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.Menu_home.AlmacenesCadena.Categorias.Almacen_categorias;
import com.example.domilaap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdaptadorA  extends RecyclerView.Adapter<RecyclerViewAdaptadorA.ViewHolder> {

    private Context mCtx;
    private List<AlmacenModelo> almacenModelo;

    public RecyclerViewAdaptadorA(Context mCtx, List<AlmacenModelo> almacenModelo) {
        this.mCtx = mCtx;
        this.almacenModelo = almacenModelo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreProducto;
        private  TextView precio;
        ImageView fotoProducto;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Almacen_categorias.class);
                    //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
                    //intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto());
                    view.getContext().startActivity(intent);
                }
            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreAlmacen);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgAlmacenCadena);
        }
    }

   public List<AlmacenModelo> productoLista;

    public RecyclerViewAdaptadorA(List<AlmacenModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptadorA.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_almacenes_cadena, parent, false);
        RecyclerViewAdaptadorA.ViewHolder viewHolder = new RecyclerViewAdaptadorA.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorA.ViewHolder holder, int position) {
        Picasso.get().load(productoLista.get(position).getFotoProducto())
                //.resize(60, 60)
                .into(holder.fotoProducto);
        holder.nombreProducto.setText(productoLista.get(position).getNombreProducto());
        holder.precio.setText(productoLista.get(position).getPrecio());
       // holder.fotoProducto.setImageResource(productoLista.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}