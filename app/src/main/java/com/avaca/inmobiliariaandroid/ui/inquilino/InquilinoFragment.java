package com.avaca.inmobiliariaandroid.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
//import com.avaca.inmobiliariaandroid.ui.inmueble.InmuebleAdapter;
import com.avaca.inmobiliariaandroid.ui.inmueble.InmuebleViewModel;

import java.util.ArrayList;

public class InquilinoFragment extends Fragment {


    private RecyclerView ReciclerViewInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private InquilinoViewModel mViewModel;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquilino, container, false);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        ReciclerViewInquilino = (RecyclerView) view.findViewById(R.id.rvInquilino);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        //Traigo desde ViewModel los inmuebles que van a ser pasados al adapter
        // Encagado de "Adaptar la info y reciclar la vista para mostrar"
        mViewModel.getToasMT().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        mViewModel.getInmueble_inquilinos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                //Seteo el recicler view con el tipo de alineacion
                ReciclerViewInquilino.setLayoutManager(linearLayoutManager);
                //Mando la info y la vista a utilizar
                inquilinoAdapter = new InquilinoAdapter(inmuebles,view,getLayoutInflater());
                // Le seteo el adapter con la info
                ReciclerViewInquilino.setAdapter(inquilinoAdapter);

            }
        });
        mViewModel.setInmueble_inquilinos();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }



}

