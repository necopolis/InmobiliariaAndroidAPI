package com.avaca.inmobiliariaandroid.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.databinding.InmuebleDetalleFragmentBinding;
import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.modelo.Inquilino;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {


    MutableLiveData<ArrayList<Inmueble>> inmueble_inquilinos ;
    ApiClient api ;
    MutableLiveData<String> toasMT;
    private String token;
    private Context context;

    public InquilinoViewModel(@NonNull Application application){
        super(application);
        context=application.getApplicationContext();
        token = ApiClient.token().getString("token", "");
    }



    public MutableLiveData<String> getToasMT() {
        if (toasMT==null){
            toasMT= new MutableLiveData<>();
        }
        return toasMT;
    }

    public MutableLiveData<ArrayList<Inmueble>> getInmueble_inquilinos() {
        if (inmueble_inquilinos==null){
            inmueble_inquilinos=new MutableLiveData<>();
        }
        return inmueble_inquilinos;
    }

    //Traigo los inmuebles alquilados

    public void setInmueble_inquilinos(){
        Call<ArrayList<Inmueble>> inmuebleCall=ApiClient.getMyApiClient().inmueblesAlquilados(token);
        inmuebleCall.enqueue(new Callback<ArrayList<Inmueble>> () {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>>  call, Response<ArrayList<Inmueble>>  response) {
                if (response.isSuccessful()){
                    if (response.body().size()>0){

                                inmueble_inquilinos.setValue(response.body());
                                toasMT.setValue("Propiedades Alquiladas Encontradas");
                                Log.d("exc","Llego a Inmubele del inquilino");
                    }else{
                        toasMT.setValue("Sin propiedades Alquiladas");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                toasMT.setValue("Error");
                Log.d("exc", t.getMessage());
            }
        });


    }
    //



}