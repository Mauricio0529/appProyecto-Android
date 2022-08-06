package com.example.domilaap.Menu_home.Farmacias;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domilaap.Menu_home.Farmacias.categoriaFarmacia.Categoria_Farmacia;
import com.example.domilaap.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdaptorF extends RecyclerView.Adapter<RecyclerViewAdaptorF.ViewHolder> {
    private Context mCtx;
    private List<FarmaciaModelo> farmaciaModelo;

    public RecyclerViewAdaptorF(Context mCtx, List<FarmaciaModelo> farmaciaModelo) {
        this.mCtx = mCtx;
        this.farmaciaModelo = farmaciaModelo;
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
                    Intent intent = new Intent(view.getContext(), Categoria_Farmacia.class);
                    //intent.putExtra("idalmacen", productoLista.get(getAdapterPosition()).getIdAlmacen());
                    //intent.putExtra("nombre", productoLista.get(getAdapterPosition()).getNombreProducto());
                    view.getContext().startActivity(intent);
                }
            });
            nombreProducto = (TextView) itemView.findViewById(R.id.tvnombreFarmacia);
            precio = (TextView) itemView.findViewById(R.id.tvMunicipio);
            fotoProducto = (ImageView) itemView.findViewById(R.id.imgFarmacia);
        }
    }


    public List<FarmaciaModelo> productoLista;

    public RecyclerViewAdaptorF(List<FarmaciaModelo> productoLista) {
        this.productoLista = productoLista;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptorF.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_farmacias, parent, false);
        RecyclerViewAdaptorF.ViewHolder viewHolder = new RecyclerViewAdaptorF.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptorF.ViewHolder holder, int position) {
        Picasso.get().load(productoLista.get(position).getFotoFarmacia())
                //.resize(60, 60)
                .into(holder.fotoProducto);
        holder.nombreProducto.setText(productoLista.get(position).getNombreFarmacia());
        holder.precio.setText(productoLista.get(position).getPrecio());
       // holder.fotoProducto.setImageResource(productoLista.get(position).getFotoFarmacia());
    }

    @Override
    public int getItemCount() { // LA CANTIDAD DE ELEMENTOS QUE SE PROCESARA
        return productoLista.size();
    }
}