package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;


import com.example.roadfinance.R;
import com.example.roadfinance.activity.helper.MyApplication;
import com.example.roadfinance.activity.model.Usuario;


public class cadastrarActivity extends AppCompatActivity {
    private EditText editTextNome, editTextSobreNome,
            editTextCpf, editTextDataNasc, editTextCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    public void PassarDados(View view) {
        editTextNome = findViewById(R.id.editNome);
        editTextSobreNome = findViewById(R.id.editSobrenome);
        editTextCpf = findViewById(R.id.editCPF);
        editTextDataNasc = findViewById(R.id.editDataNasc);
        editTextCelular = findViewById(R.id.editCelular);

        if (!editTextNome.getText().toString().isEmpty()) {
            if (!editTextSobreNome.getText().toString().isEmpty()) {
                if (!editTextCpf.getText().toString().isEmpty()) {
                    if (!editTextDataNasc.getText().toString().isEmpty()) {
                        if (!editTextCelular.getText().toString().isEmpty()) {
                            MyApplication.getInstance().setNome(editTextNome.getText().toString());
                            MyApplication.getInstance().setSobre_nome(editTextSobreNome.getText().toString());
                            MyApplication.getInstance().setCpf(editTextCpf.getText().toString());
                            MyApplication.getInstance().setData_nas(editTextDataNasc.getText().toString());
                            MyApplication.getInstance().setCelular(editTextCelular.getText().toString());
                            Intent intent = new Intent(this, cadastrarTwoActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(cadastrarActivity.this,
                                    "Preencha o Celular",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cadastrarActivity.this,
                                "Preencha a Data de nascimento",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(cadastrarActivity.this,
                            "Preencha o CPF",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(cadastrarActivity.this,
                        "Preencha o Sobre Nome",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(cadastrarActivity.this,
                    "Preencha o Nome",
                    Toast.LENGTH_SHORT).show();
        }


    }

}
