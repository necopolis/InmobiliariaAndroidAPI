package com.avaca.inmobiliariaandroid.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avaca.inmobiliariaandroid.Login;
import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.databinding.FragmentPerfilBinding;
import com.avaca.inmobiliariaandroid.modelo.Propietario;

public class PerfilFragment extends Fragment {


    private PerfilViewModel mViewModel;
    private FragmentPerfilBinding binding;
    private Toast toast;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel=new ViewModelProvider(this).get(PerfilViewModel.class);
        binding= FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Cargo los datos del usuario Actual
        mViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.edtDNI.setText(propietario.getDni().toString());
                binding.edtNombre.setText(propietario.getNombre());
                binding.edtApellido.setText(propietario.getApellido());
                binding.edtEmail.setText(propietario.getEmail());
                binding.edtPassword.setText("");
                binding.edtTelefono.setText(propietario.getTelefono());
            }
        });
        mViewModel.getCamposMT().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.edtDNI.setEnabled(aBoolean);
                binding.edtNombre.setEnabled(aBoolean);
                binding.edtApellido.setEnabled(aBoolean);
                binding.edtEmail.setEnabled(aBoolean);
                binding.edtPassword.setEnabled(aBoolean);
                binding.edtTelefono.setEnabled(aBoolean);
            }
        });

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.editarDatos();
            }
        });

        mViewModel.getBTNEditarMT().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.btnEditar.setEnabled(aBoolean);
            }
        });
        mViewModel.getBTNGuardarMT().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.btnGuardar.setEnabled(aBoolean);
            }
        });
        mViewModel.gettoastMT().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();

            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario EditadoP = new Propietario();
                EditadoP.setDni(Long.parseLong(binding.edtDNI.getText().toString()));
                EditadoP.setNombre(binding.edtNombre.getText().toString());
                EditadoP.setApellido(binding.edtApellido.getText().toString());
                EditadoP.setEmail(binding.edtEmail.getText().toString());
                EditadoP.setContraseña(binding.edtPassword.getText().toString());
                EditadoP.setTelefono(binding.edtTelefono.getText().toString());
                EditadoP.setActivo(true);
                //Se modifico la api para que se vean los cambios realizados
                mViewModel.GuardarDatos(EditadoP);
                mViewModel.bloquearCampor();
            }
        });
        mViewModel.PropietarioActual();
        mViewModel.bloquearCampor();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}