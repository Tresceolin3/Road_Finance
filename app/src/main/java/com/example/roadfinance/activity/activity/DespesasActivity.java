package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.helper.DateUtil;
import com.example.roadfinance.activity.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescrição);

        //Preenche o campo data com a date atual
        campoData.setText(DateUtil.dataAtual());

    }

    public void salvarDespesa(View view) {
        if (validarCamposDespesa()) {
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setDate(campoData.getText().toString());
            movimentacao.setTipo("d");
            movimentacao.Salvar(data);
        }
    }

    public Boolean validarCamposDespesa() {

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()) {
                if (!textoCategoria.isEmpty()) {
                    if (!textoDescricao.isEmpty()) {
                    } else {
                        Toast.makeText(DespesasActivity.this,
                                "Descrição não foi preenchida!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DespesasActivity.this,
                            "Categoria não foi preenchida",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DespesasActivity.this,
                        "Data não foi preenchida",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DespesasActivity.this,
                    "Valor não foi preenchido",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}
