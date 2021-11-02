package com.avaca.inmobiliariaandroid.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Garante;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.modelo.Inquilino;
import com.avaca.inmobiliariaandroid.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {

    //ApiClient api;
    private Inmueble inmuebleAux;
    private Inquilino inq;
    private Contrato con;
    private Garante gar;
    private MutableLiveData<Inquilino> inquilinoMT;
    private String token;
    private Context context;

    public InquilinoDetalleViewModel(@NonNull Application application){
        super(application);
        context=application.getApplicationContext();
        token = ApiClient.token().getString("token", "");
        inq=new Inquilino();
        gar=new Garante();
        con=new Contrato();
    }


    public MutableLiveData<Inquilino> getInquilinoMT(){
        if (inquilinoMT==null){
            inquilinoMT= new MutableLiveData<>();
        }
        return inquilinoMT;
    }
    //Actualizo el estado de disponible, el inmuble viaja desde el adapter

    //Se lo asigno a mi mutable
    public void setInquilinoMT(Bundle bundle){
        inmuebleAux=(Inmueble) bundle.getSerializable("inmueble");
        Call<Contrato> inquilinoCall=ApiClient.getMyApiClient().inquilinoPorInmueble(token, inmuebleAux.getIdInmueble());
        inquilinoCall.enqueue(new Callback<Contrato> () {
            @Override
            public void onResponse(Call<Contrato>   call, Response<Contrato>   response) {
                if (response.isSuccessful()|| response.body() != null){
                    inq.setNombre(response.body().getInquilino().getNombre());
                    inq.setId(response.body().getInquilino().getId());
                    inq.setApellido(response.body().getInquilino().getApellido());
                    inq.setDni(response.body().getInquilino().getDni());
                    inq.setEmail(response.body().getInquilino().getEmail());
                    inq.setActivo(response.body().getInquilino().isActivo());
                    inq.setTelefono(response.body().getInquilino().getTelefono());
                    inq.setLugarTrabajo(response.body().getInquilino().getLugarTrabajo());
                    inq.setGarante(response.body().getGarante());
                    //inq.getGarante().setTelefono("Perez");

                        inquilinoMT.setValue(inq);
                        //toasMT.setValue("Propiedades Alquiladas Encontradas");
                        Log.d("exc","Llego a inquilino");
                    }else{
                        //toasMT.setValue("Sin propiedades Alquiladas");
                    }

            }

            @Override
            public void onFailure(Call<Contrato>  call, Throwable t) {
                //toasMT.setValue("Error");
                Log.d("exc", t.getMessage());
            }
        });
            //inquilinoMT.setValue(inq);
    }


}