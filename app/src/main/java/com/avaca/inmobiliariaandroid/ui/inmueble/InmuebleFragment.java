package com.avaca.inmobiliariaandroid.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.FragmentInmuebleBinding;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;

import java.util.ArrayList;

public class InmuebleFragment extends Fragment {


    private RecyclerView ReciclerViewInmueble;
    private InmuebleViewModel mViewModel;
    private InmuebleAdapter inmuebleAdapter;

    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmueble, container, false);
        mViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        ReciclerViewInmueble = (RecyclerView) view.findViewById(R.id.rvInmueble);

       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        //Traigo desde ViewModel los inmuebles que van a ser pasados al adapter
        // Encagado de "Adaptar la info y reciclar la vista para mostrar"
        mViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                //Seteo el recicler view con el tipo de alineacion
                ReciclerViewInmueble.setLayoutManager(linearLayoutManager);
                //Mando la info y la vista a utilizar
                inmuebleAdapter = new InmuebleAdapter(inmuebles,view,getLayoutInflater());
                // Le seteo el adapter con la info
                ReciclerViewInmueble.setAdapter(inmuebleAdapter);
            }
        });
        mViewModel.setInmuebles();
        return view;
    }




}