package com.avaca.inmobiliariaandroid;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.avaca.inmobiliariaandroid.modelo.Propietario;
import com.avaca.inmobiliariaandroid.request.ApiClient;
import com.google.android.material.shadow.ShadowRenderer;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * */
public class LoginViewModel extends AndroidViewModel {
    // Atributos
    //Mutables
    private MutableLiveData<String> mensajeNoLogin;
    private Context context;
    private SharedPreferences sp;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        sp = ApiClient.conectar(context);
    }
    public LiveData<String> getMensajeNoLogin(){
        if(mensajeNoLogin == null){
            mensajeNoLogin = new MutableLiveData<>();
        }
        return mensajeNoLogin;
    }

    public void iniciarSesion(String email, String password){
        Call<String> token= ApiClient.getMyApiClient().login(email,password);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer "+response.body());
                    editor.commit();
                    mensajeNoLogin.setValue("Bienvenido");
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("exc",call.toString());
                Log.d("exc",t.getMessage());
                Log.d("exc","Paso por aca, loginViewModel");
                mensajeNoLogin.setValue("Usuario o contraseña incorrecta!!");
            }
        });

    }
}
