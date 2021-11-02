package com.avaca.inmobiliariaandroid.ui.pago;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Pago;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends ViewModel {
    private ApiClient api;
    private MutableLiveData<ArrayList<Pago>> pagosList;
    private String token;
    public PagoViewModel(){
        token = ApiClient.token().getString("token", "");

    }
    public MutableLiveData<ArrayList<Pago>> getpagosList() {
        if (pagosList == null) {
            pagosList = new MutableLiveData<>();
        }
        return pagosList;
    }

    public void setpagosList(Bundle bundle) {
        Contrato con=(Contrato)bundle.getSerializable("contrato");
        Call<ArrayList<Pago>> listCall=ApiClient.getMyApiClient().pagosPorContrato(token,con.getId());
        listCall.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful()){
                    if (response.body().size()>0){
                        pagosList.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Log.d("exc", "Error");
            }
        });




    }
}