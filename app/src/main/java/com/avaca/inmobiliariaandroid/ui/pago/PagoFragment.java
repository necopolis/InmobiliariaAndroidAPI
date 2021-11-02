package com.avaca.inmobiliariaandroid.ui.pago;

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

import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.PagoFragmentBinding;
import com.avaca.inmobiliariaandroid.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {


    private PagoViewModel mViewModel;
    private PagoFragmentBinding binding;
    private PagoAdapter adapter;
    private RecyclerView recyclerViewLista;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        binding = PagoFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewLista = binding.rvPago;

        mViewModel.getpagosList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago>  pagos) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                );
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new PagoAdapter(root, pagos);
                recyclerViewLista.setAdapter(adapter);
            }
        });

        mViewModel.setpagosList(getArguments());
        return root;
    }
}