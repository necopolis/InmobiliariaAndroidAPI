package com.avaca.inmobiliariaandroid.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.MiViewHolder> {
    private List<Inmueble> inmuebleList;
    private View context;
    private LayoutInflater inflaterVista;


    public InmuebleAdapter(List<Inmueble> inmuebleList, View view, LayoutInflater inflater) {
        this.inmuebleList=inmuebleList;
        this.context=view;
        this.inflaterVista=inflater;

    }

    @NonNull
    @Override
    public InmuebleAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //######## Designo la vista, item inmueble y la paso a mi holder para llenar los campos #########
        View view =inflaterVista.inflate(R.layout.item_inmueble, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.MiViewHolder holder, int position) {
            Inmueble inmueble= inmuebleList.get(position);
            Glide.with(context)
                    .load(ApiClient.getConecion()+inmueble.getImagen())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imagenInmueble);
            holder.direccion.setText(inmueble.getDireccion());
            holder.precio.setText(inmueble.getPrecio()+" ");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("inmueble", inmueble);
                    Navigation.findNavController(view).navigate(R.id.inmuebleDetalleFragment, bundle);
                }
            });
    }

    @Override
    public int getItemCount() {
        return inmuebleList.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        //Elementos de la vista
        private ImageView imagenInmueble;
        private TextView direccion;
        private TextView precio;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenInmueble=itemView.findViewById(R.id.ivInmueble);
            direccion=itemView.findViewById(R.id.tvDireccionItemInmuble);
            precio=itemView.findViewById(R.id.tvPrecioItemInmuebleInquilino);
        }


    }


}
