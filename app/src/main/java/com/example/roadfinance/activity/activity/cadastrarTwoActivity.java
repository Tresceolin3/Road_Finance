package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.helper.MyApplication;

public class cadastrarTwoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_two);

    }

    public void RepassarDados(View view) {
        EditText editTextCep = findViewById(R.id.editCEP);
        EditText editTextCidade = findViewById(R.id.editCidade);
        EditText editTextRua = findViewById(R.id.editRua);
        EditText editTextNrua = findViewById(R.id.editNrua);
        EditText editTextBairro = findViewById(R.id.editBairro);

        if (!editTextCep.getText().toString().isEmpty()) {
            if (!editTextCidade.getText().toString().isEmpty()) {
                if (!editTextRua.getText().toString().isEmpty()) {
                    if (!editTextNrua.getText().toString().isEmpty()) {
                        if (!editTextBairro.getText().toString().isEmpty()) {

                            MyApplication.getInstance().setCep(editTextCep.getText().toString());
                            MyApplication.getInstance().setCidade(editTextCidade.getText().toString());
                            MyApplication.getInstance().setRua(editTextRua.getText().toString());
                            MyApplication.getInstance().setNrua(editTextNrua.getText().toString());
                            MyApplication.getInstance().setBairro(editTextBairro.getText().toString());
                            Intent intent = new Intent(this, cadastrarTreeActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(cadastrarTwoActivity.this,
                                    "Preencha o Bairro",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cadastrarTwoActivity.this,
                                "Preencha o Número da Rua",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(cadastrarTwoActivity.this,
                            "Preencha a Rua",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(cadastrarTwoActivity.this,
                        "Preencha a Cidade",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(cadastrarTwoActivity.this,
                    "Preencha o CEP",
                    Toast.LENGTH_SHORT).show();
        }

    }


}
