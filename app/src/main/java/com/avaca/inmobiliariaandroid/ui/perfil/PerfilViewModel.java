package com.avaca.inmobiliariaandroid.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Propietario;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {


    // TODO: Implement the ViewModel
    private ApiClient api;
    private Propietario paux;
    private MutableLiveData<Boolean> CamposMT;
    private MutableLiveData<Propietario> propietarioMT;
    private MutableLiveData<Boolean> BTNEditarMT;
    private MutableLiveData<Boolean> BTNGuardarMT;
    private MutableLiveData<String> toastMT;
    private Context context;
    private String token;

    public PerfilViewModel(@NonNull Application application){
        super(application);
        context=application.getApplicationContext();
        token = ApiClient.token().getString("token", "");
        //propietarioMT.setValue(api.obtenerUsuarioActual());

    }
    public MutableLiveData<Boolean> getCamposMT(){
        if (CamposMT==null){
            CamposMT=new MutableLiveData<>();
        }
        return CamposMT;
    }

    public LiveData<Propietario> getPropietario(){
        if (propietarioMT==null){
            propietarioMT= new MutableLiveData<>();
        }
        return propietarioMT;
    }

    public MutableLiveData<Boolean> getBTNEditarMT(){
        if (BTNEditarMT==null){
            BTNEditarMT= new MutableLiveData<>();
        }
        return BTNEditarMT;
    }
    public MutableLiveData<String> gettoastMT(){
        if (toastMT==null){
            toastMT= new MutableLiveData<>();
        }
        return toastMT;
    }
    public MutableLiveData<Boolean> getBTNGuardarMT(){
        if (BTNGuardarMT==null){
            BTNGuardarMT= new MutableLiveData<>();
        }
        return BTNGuardarMT;
    }

    public void editarDatos(){
        this.CamposMT.setValue(true);
        this.BTNGuardarMT.setValue(true);
        this.BTNEditarMT.setValue(false);
    }

    public void GuardarDatos(Propietario p){
        //this.api.actualizarPerfil(p);
        Log.d("exc", "Propietario");
        Log.d("exc", p.getApellido()+"  "+p.getEmail()+" "+p.getContraseña()+" "+ p.getActivo());
        Call<Propietario> propietarioPerfil= ApiClient.getMyApiClient().actualizarPerfil(token,p);
        propietarioPerfil.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    BTNGuardarMT.setValue(false);
                    BTNEditarMT.setValue(true);
                    CamposMT.setValue(false);
                    Log.d("exc", "Entro a actualizar perfil");
                    toastMT.setValue("Datos guardados correctamente");
                }else{
                    toastMT.setValue("No se Guardo");
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                toastMT.setValue("Error");
                Log.d("exc",t.getMessage());
            }
        });
        propietarioMT.setValue(p);


    }
    public void bloquearCampor(){
        this.CamposMT.setValue(false);
    }
    //
    public void ActualizarPantalla(){
        PropietarioActual();
        this.CamposMT.setValue(false);
    }
    public void PropietarioActual(){
        Call<Propietario> propietarioCall= ApiClient.getMyApiClient().obtenerUsuarioActual(token);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    propietarioMT.setValue(response.body());
                }else {
                    toastMT.setValue("No Trajo al proietario");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                toastMT.setValue("Error");
                Log.d("exc",t.getMessage());
            }
        });


    }



}