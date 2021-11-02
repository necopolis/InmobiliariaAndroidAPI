package com.avaca.inmobiliariaandroid.ui.inquilino;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


import java.util.ArrayList;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.MiViewHolder> {

    private ArrayList<Inmueble> inmuebleList;
    private View context;
    private LayoutInflater inflaterVista;


    public InquilinoAdapter(ArrayList<Inmueble> inmuebleList, View view, LayoutInflater inflater) {
        this.inmuebleList=inmuebleList;
        this.context=view;
        this.inflaterVista=inflater;

    }

    @NonNull
    @Override
    public InquilinoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //######## Designo la vista, item inmueble y la paso a mi holder para llenar los campos #########
        View view =inflaterVista.inflate(R.layout.item_inquilino, parent, false);
        return new InquilinoAdapter.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.MiViewHolder holder, int position) {
        Inmueble inmueble= inmuebleList.get(position);
        Glide.with(context.getContext())
                .load(ApiClient.getConecion() +inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        Log.d("exc", "exc:"+inmueble.getDireccion().toString());
        holder.dir.setText(inmueble.getDireccion().toString());
        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController(context).navigate(R.id.inquilinoDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebleList.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        //Elementos de la vista
        private ImageView img;
        private TextView dir;
        private Button btnVer;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ivContratoItem);
            dir=itemView.findViewById(R.id.tvDireContratoItem);
            btnVer=itemView.findViewById(R.id.btnVerInmuebleInquilino);
        }


    }


}
