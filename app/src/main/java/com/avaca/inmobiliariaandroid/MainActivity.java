package com.avaca.inmobiliariaandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avaca.inmobiliariaandroid.modelo.Propietario;
import com.avaca.inmobiliariaandroid.request.ApiClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.avaca.inmobiliariaandroid.databinding.ActivityMainBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        context=getApplicationContext();
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
/*
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

 */
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View header= navigationView.getHeaderView(0);
        TextView nombreProp=header.findViewById(R.id.tvNombrePropietario);
        TextView emailProp=header.findViewById(R.id.tvEmailPropietario);
        ImageView ivPropietario=header.findViewById(R.id.ivPropietario);


        String token = ApiClient.token().getString("token", "");
        Call<Propietario> propietarioCall= ApiClient.getMyApiClient().obtenerUsuarioActual(token);
        Log.d("exc", "Main Activity");
        Log.d("exc", token);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
//                Log.d("exc", response.body().getNombre());
                nombreProp.setText(response.body().getNombre() + " " + response.body().getApellido());
                emailProp.setText(response.body().getEmail());
                    Glide.with(context.getApplicationContext())
                            .load(ApiClient.getConecion()+response.body().getAvatar())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivPropietario);

//                emailProp.setText(response.body().getNombre());
                Toast.makeText(context, "Datos encontrados", Toast.LENGTH_SHORT).show();
                Log.d("exc", response.message());
                }else{
                    Toast.makeText(context, "Algo salio mal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("exc", t.getMessage());
            }
        });

        //Propietario p= api.obtenerUsuarioActual();
        //ImageView ivPropietario=header.findViewById(R.id.ivPropietario);
        //nombreProp.setText(p.+"  "+p.getApellido());
        //emailProp.setText(p.getEmail());
        //ivPropietario.setImageResource(p.getAvatar());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_map,R.id.nav_perfil,R.id.nav_inmueble, R.id.nav_inquilino, R.id.nav_contrato, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}