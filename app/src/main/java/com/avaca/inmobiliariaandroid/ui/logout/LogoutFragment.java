package com.avaca.inmobiliariaandroid.ui.logout;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avaca.inmobiliariaandroid.Login;
import com.avaca.inmobiliariaandroid.R;
import com.avaca.inmobiliariaandroid.request.ApiClient;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel=new ViewModelProvider(this).get(LogoutViewModel.class);
        new AlertDialog.Builder(getContext())
                .setTitle("Cierre de sesion")
                .setMessage("¿Quiere salir de la aplicación?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), Login.class);
                        ApiClient.eliminarToken(getContext());
                        Log.d("exc", "Elimine Token");
                        getContext().startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getView()).popBackStack();
                    }
                })
                .show();
        return inflater.inflate(R.layout.logout_fragment, container, false);
    }


}