package com.avaca.inmobiliariaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Toast toast;
    private TextView smsNoLogin;
    private LoginViewModel loginViewModel;
    private ImageView imgLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarVista();

        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        loginViewModel.getMensajeNoLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.iniciarSesion(username.getText().toString(),
                        password.getText().toString());
            }
        });
    }

    private void iniciarVista(){
        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);




    }
}