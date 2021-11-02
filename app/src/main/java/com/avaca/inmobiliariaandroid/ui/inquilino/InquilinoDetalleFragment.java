package com.avaca.inmobiliariaandroid.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.InmuebleDetalleFragmentBinding;
import com.avaca.inmobiliariaandroid.databinding.InquilinoDetalleFragmentBinding;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.modelo.Inquilino;
import com.avaca.inmobiliariaandroid.ui.inmueble.InmuebleDetalleFragment;
import com.avaca.inmobiliariaandroid.ui.inmueble.InmuebleDetalleViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel mViewModel;
    private InquilinoDetalleFragmentBinding binding;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=InquilinoDetalleFragmentBinding.inflate(inflater, container, false);
        View context=binding.getRoot();
        mViewModel=new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);

        mViewModel.getInquilinoMT().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigoInquilinoDetalle.setText(inquilino.getId()+"");
                binding.tvNombreInquilinoDetalle.setText(inquilino.getNombre().toString());
                binding.tvApellidoInquilinoDetalle.setText(inquilino.getApellido().toString());
                binding.tvDNIInquilinoDetalle.setText(inquilino.getDni()+"");
                binding.tvEmailInquilinoDetalle.setText(inquilino.getEmail().toString());
                binding.tvTelefonoInquilinoDetalle.setText(inquilino.getTelefono().toString());
                binding.tvGaranteInquilinoDetalle.setText(inquilino.getGarante().getNombre().toString());
                binding.tvTelefonoGaranteInquilinoDetalle.setText(inquilino.getGarante().getTelefono().toString());

            }
        });

        mViewModel.setInquilinoMT(getArguments());
        return context;
    }



}



