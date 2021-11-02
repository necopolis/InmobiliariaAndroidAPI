package com.avaca.inmobiliariaandroid.ui.inmueble;

import android.app.Application;
import android.media.AsyncPlayer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmuebles ;
    private String token;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        token = ApiClient.token().getString("token", "");
        this.inmuebles = new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {

        return inmuebles;
    }
    //
    public void setInmuebles(){
        Call<ArrayList<Inmueble>> arrayListCall= ApiClient.getMyApiClient().listaDeInmuebles(token);
        arrayListCall.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if (response.isSuccessful()){
                    inmuebles.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                Log.d("exc", "Error no encontro inmuebles");
            }
        });


    }


}