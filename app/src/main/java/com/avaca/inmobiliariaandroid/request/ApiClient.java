package com.avaca.inmobiliariaandroid.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.avaca.inmobiliariaandroid.modelo.Contrato;
import com.avaca.inmobiliariaandroid.modelo.Garante;
import com.avaca.inmobiliariaandroid.modelo.Inmueble;
import com.avaca.inmobiliariaandroid.modelo.Inquilino;
import com.avaca.inmobiliariaandroid.modelo.Pago;
import com.avaca.inmobiliariaandroid.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;


public class ApiClient {
    private static final String UrlBase = "http://192.168.0.106:45457/api/";
    private static PostInterface postInterface;
    private static SharedPreferences token;
    private static final String conecion= "http://192.168.0.106:45457/";;

    public static SharedPreferences conectar(Context context) {
        if (token==null){
            token=context.getSharedPreferences("token.dat", 0);
        }
        return token;
    }
    public static SharedPreferences token() {
        return token;
    }
    public static void eliminarToken(Context context) {
        context.getSharedPreferences("token.dat", 0).edit().clear().commit();
    }
    public static String getConecion(){
        return conecion;
    }

    public static PostInterface getMyApiClient() {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface = retrofit.create(PostInterface.class);

        return postInterface;
    }


    public interface PostInterface {
        //Propietarios
        @FormUrlEncoded
        //Login
        @POST("Propietarios/login")
        Call<String> login(
                @Field("Usuario") String usuario,
                @Field("Clave") String clave);

        //Perfil
        @GET("Propietarios")
        Call<Propietario> obtenerUsuarioActual(
                @Header("Authorization") String token);

        //ModificarPerfil
        @PUT("Propietarios")
        Call<Propietario> actualizarPerfil(
                @Header("Authorization") String token,
                @Body Propietario pn);

        //Inmuebles
        //Lista de Inmuebles
        @GET("inmuebles")
        Call<ArrayList<Inmueble>> listaDeInmuebles(
                @Header("Authorization") String token);

        //Cambiar Disponibilidad
        @PUT("inmuebles/{id}")
        Call<Inmueble> cambiarDisponibilidad(
                @Header("Authorization") String token,
                @Path("id") int idInmueble);

        @GET("inmuebles/alquilados")
        Call<ArrayList<Inmueble>> inmueblesAlquilados(
                @Header("Authorization") String token);

        //Inquilinos
        @GET("Inquilinos/contratos")
        Call<ArrayList<Inmueble>> inmuebleContrato(
                @Header("Authorization") String token);


        @GET("Inquilinos/{id}")
        Call<Contrato>  inquilinoPorInmueble(
                @Header("Authorization") String token,
                @Path("id") int InmuebleId);

        @GET("Inquilinos")
        Call<Inquilino> inquilinoPorInmuebleOpc(
                @Header("Authorization") String token,
                @Body Inmueble inmueble);

        //Garante
        @GET("Garante/{id}")
        Call<Garante> garantePorInmueble(@Header("Authorization") String token, @Path("id") int inmuebleId);


        //Contratos
        @GET("Contratos/inmuebles")
        Call<ArrayList<Inmueble>> contratoInmuebles(@Header("Authorization") String token);



        @GET("Contratos/{id}")
        Call<Contrato> contratoPorInmuebles(@Header("Authorization") String token, @Path("id") int inmuebleId);

        @GET("Contratos")
        Call<ArrayList<Contrato>> contratoTodos(@Header("Authorization") String token);

        @GET("pagos/contrato/{id}")
        Call<ArrayList<Inmueble>> pagosContrato(@Header("Authorization") String token, @Path("id") int contratoId );


        @PUT("Inmuebles")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        @PUT("Inmuebles")
        Call<Inmueble> actualizar(@Header("Authorization") String token,@Body Inmueble idInmueble);

        @GET("Pagos/{id}")
        Call<ArrayList<Pago>> pagosPorContrato(@Header("Authorization") String token, @Path("id") int contratoId);

    }

}