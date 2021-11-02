package com.avaca.inmobiliariaandroid.ui.contrato;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends ViewModel {


    MutableLiveData<ArrayList<Inmueble>> inmuebleLista ;
    ApiClient api;
    MutableLiveData<String> toast;
    private String token;
    public ContratoViewModel(){
        //api = ApiClient.getApi();
        token = ApiClient.token().getString("token", "");
    }

    public MutableLiveData<String> getToast() {
        if (toast==null){
            toast=new MutableLiveData<>();
        }
        return toast;
    }

    public MutableLiveData<ArrayList<Inmueble>> getinmuebleLista() {
        if (inmuebleLista== null){
            inmuebleLista=new MutableLiveData<>();
        }
        return inmuebleLista;
    }


    public void setinmuebleLista(){
        Call<ArrayList<Inmueble>> inmuebleCall=ApiClient.getMyApiClient().inmueblesAlquilados(token);
        inmuebleCall.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>>  call, Response<ArrayList<Inmueble>> response) {
                if (response.isSuccessful()){
                    if (response.body().size()>0){
                        inmuebleLista.setValue(response.body());
                        toast.setValue("Propiedades Alquiladas Encontradas");
                        Log.d("exc","Llego a Inmubele del inquilino");
                    }else{
                        toast.setValue("Sin propiedades Alquiladas");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                toast.setValue("Error");
                Log.d("exc", t.getMessage());
            }
        });

    }


}