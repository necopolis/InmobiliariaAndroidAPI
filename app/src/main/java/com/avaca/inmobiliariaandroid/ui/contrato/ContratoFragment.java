package com.avaca.inmobiliariaandroid.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.FragmentContratoBinding;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {


    private RecyclerView rvContrato;
    private ContratoAdapter contratoAdapter;
    private ContratoViewModel mViewModel;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contrato, container, false);
        rvContrato = (RecyclerView) root.findViewById(R.id.rvContrato);
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getToast().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        mViewModel.getinmuebleLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rvContrato.setLayoutManager(linearLayoutManager);
                contratoAdapter = new ContratoAdapter(inmuebles,root,getLayoutInflater());

                rvContrato.setAdapter(contratoAdapter);
            }
        });
        mViewModel.setinmuebleLista();
        return root;
    }


}