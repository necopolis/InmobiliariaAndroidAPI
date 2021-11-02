package com.avaca.inmobiliariaandroid.ui.inmueble;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {

    ApiClient api;
    Inmueble inmuebleAux;
    MutableLiveData<Inmueble> inmuebleMT;
    private String token;

    public InmuebleDetalleViewModel(@NonNull Application application){
        super(application);
        token = ApiClient.token().getString("token", "");
        //api=ApiClient.getApi();
    }

    public MutableLiveData<Inmueble> getInmuebleMT(){
        if (inmuebleMT==null){
            inmuebleMT= new MutableLiveData<>();
        }
        return inmuebleMT;
    }
    //Actualizo el estado de disponible, el inmuble viaja desde el adapter
    public void actualizarEstado(boolean disponible){
        inmuebleAux.setEstado(disponible);
        Call<Inmueble> inmuebleCall= ApiClient.getMyApiClient().actualizarInmueble(token,inmuebleAux);
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                //api.actualizarInmueble(inmuebleAux);
                if (response.isSuccessful()){
                    Log.d("exc", "Cambio estado");
                    //inmuebleMT.setValue(response.body());
                }
                else{
                    Log.d("exc", "Error en traer inmueble");
                }

            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

                Log.d("exc", "Error");
            }
        });

    }

    //Se lo asigno a mi mutable
    public void setInmuebleMT(Bundle bundle){
        inmuebleAux=(Inmueble) bundle.getSerializable("inmueble");
        inmuebleMT.setValue(inmuebleAux);
    }



}