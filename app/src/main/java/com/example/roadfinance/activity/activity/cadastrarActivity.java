package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;


import com.example.roadfinance.R;


public class cadastrarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);


    }

    public void PassarDados(View view){
        EditText editTextNome = findViewById(R.id.editNome);
        String nome = editTextNome.getText().toString();
        Intent intent = new Intent(cadastrarActivity.this, cadastrar2Activity.class);
        intent.putExtra("nome", nome);
       startActivity(intent);
       finish();
    }


}
