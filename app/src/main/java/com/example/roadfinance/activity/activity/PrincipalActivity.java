package com.example.roadfinance.activity.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.roadfinance.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public  void adicionarReceita(View view){
       startActivity(new Intent(this,ReceitasActivity.class));

    }

    public  void adicionarDespesa(View view){
        startActivity(new Intent(this,DespesasActivity.class));
    }

    public void adicionarTruck(View view){
        startActivity(new Intent(this, CadastrarTruckActivity.class));
    }

}
