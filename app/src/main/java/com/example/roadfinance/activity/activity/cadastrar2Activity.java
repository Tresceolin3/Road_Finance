package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roadfinance.R;

public class cadastrar2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar2);
    }

    public void buttonSenha(View view){
        startActivity(new Intent(this, cadastrar3Activity.class));
    }

}
