package com.avaca.inmobiliariaandroid.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.InmuebleDetalleFragmentBinding;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private InmuebleDetalleFragmentBinding binding;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=InmuebleDetalleFragmentBinding.inflate(inflater, container, false);
        View context=binding.getRoot();
        mViewModel=new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        inicializar();
        mViewModel.setInmuebleMT(getArguments());
        return context;
    }

    public void inicializar(){
        mViewModel.getInmuebleMT().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {


                Glide.with(getContext())
                        .load(ApiClient.getConecion() +inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imInmuebleDetalle);
                Log.d("exc", "PASO POR ACA");
                //Log.d("exc", inmueble.getImagen());

                binding.tvCodigoDetalle.setText(inmueble.getIdInmueble()+"");
                binding.tvAmbientesDetalle.setText(inmueble.getAmbientes()+"");
                binding.tvDireccionDetalle.setText(inmueble.getDireccion().toString());
                binding.tvPrecioDetalle.setText(inmueble.getPrecio()+"");
                binding.tvUsoDetalle.setText(inmueble.getUsoNombre().toString());
                binding.tvTipoDetalle.setText(inmueble.getTipoNombre().toString());
                binding.cbDisponibleDetalle.setChecked(inmueble.isEstado());
                binding.cbDisponibleDetalle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.actualizarEstado(binding.cbDisponibleDetalle.isChecked());
                    }
                });

            }
        });
    }



}