package com.avaca.inmobiliariaandroid.ui.contrato;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends ViewModel {

    ApiClient api;
    Inmueble inmuebleAux;
    MutableLiveData<Contrato> contratoMT;
    private String token;
    public ContratoDetalleViewModel(){
        //api=ApiClient.getApi();
        token = ApiClient.token().getString("token", "");
    }

    public MutableLiveData<Contrato> getcontratoMT(){
        if (contratoMT==null){
            contratoMT= new MutableLiveData<>();
        }
        return contratoMT;
    }

    //Se lo asigno a mi mutable
    public void setcontratoMT(Bundle bundle){
        inmuebleAux=(Inmueble) bundle.getSerializable("inmueble");
        Call<Contrato> contratoCall=ApiClient.getMyApiClient().contratoPorInmuebles(token, inmuebleAux.getIdInmueble());
        contratoCall.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()){
                    contratoMT.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("exc", "Error");
            }
        });

    }

}