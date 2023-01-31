package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roadfinance.R;

public class cadastrar2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar2);

    }

    public void RepassarDados(View view){
        String nome = getIntent().getStringExtra("nome");
        Intent intent = new Intent(cadastrar2Activity.this, cadastrar3Activity.class);
        intent.putExtra("nome",nome);
        startActivity(intent);
        finish();
    }



}
