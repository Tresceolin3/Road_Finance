package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roadfinance.R;

import java.io.Serializable;

public class cadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

    }

    public void btProximo(View view){
        startActivity(new Intent(this, cadastrar2Activity.class));
        fecharCadastrarActivity();
    }

    public void fecharCadastrarActivity(){
        finish();
    }
}
