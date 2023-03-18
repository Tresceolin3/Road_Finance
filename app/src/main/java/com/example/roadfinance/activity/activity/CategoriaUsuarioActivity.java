package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.helper.MyApplication;

public class CategoriaUsuarioActivity extends AppCompatActivity {

    private RadioButton radioButtonMotorista, radioButtonMecanico,radioButtonProprietario;
    private String categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_usuario);

        radioButtonMotorista = findViewById(R.id.radioButtonMotorista);
        radioButtonMecanico = findViewById(R.id.radioButtonMecanico);
        radioButtonProprietario = findViewById(R.id.radioButtonProprietario);


    }


    public void Continuar(View view){
        Intent intent = new Intent(this, cadastrarActivity.class);

        if(radioButtonMotorista.isChecked()){
            categoria = "Motorista";
            MyApplication.getInstance().setCategoria(categoria);
            startActivity(intent);
            finish();
        }else if(radioButtonProprietario.isChecked()){
            categoria = "Proprietario";
            MyApplication.getInstance().setCategoria(categoria);
            startActivity(intent);
            finish();
        }else if(radioButtonMecanico.isChecked()){
            categoria = "Mecanico";
            MyApplication.getInstance().setCategoria(categoria);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(CategoriaUsuarioActivity.this,
                    "Selecione algumas das opções",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
