package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.helper.DateUtil;
import com.example.roadfinance.activity.model.MovimentacaoViagem;

public class CadastrarViagemActivity extends AppCompatActivity {
    private EditText campoOrigem, campoDestino, campoData, campoValorFrete,
            campoPlaca, campoQuilometragem;
    private MovimentacaoViagem movimentacaoViagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_viagem);

        campoOrigem = findViewById(R.id.editOrigem);
        campoDestino = findViewById(R.id.editDestino);
        campoData = findViewById(R.id.editDataViagem);
        campoValorFrete = findViewById(R.id.editValorFrete);
        campoPlaca = findViewById(R.id.editPlaca);
        campoQuilometragem = findViewById(R.id.editQuilometragem);

        campoData.setText(DateUtil.dataAtual());


    }

    public void SalvarViagem(View view) {
        if (validarCamposViagem()) {
            movimentacaoViagem = new MovimentacaoViagem();
            String data = campoData.getText().toString();
            Double valor = Double.parseDouble(campoValorFrete.getText().toString());

            movimentacaoViagem.setValorFrete(valor);
            movimentacaoViagem.setOrigem(campoOrigem.getText().toString());
            movimentacaoViagem.setDestino(campoDestino.getText().toString());
            movimentacaoViagem.setDataViagem(campoData.getText().toString());
            movimentacaoViagem.setPlaca(campoPlaca.getText().toString());
            movimentacaoViagem.setQuilometragem(campoQuilometragem.getText().toString());

            movimentacaoViagem.SalvarViagem(data);
            finish();
        }

    }


    public Boolean validarCamposViagem() {

        String textoOrigem = campoOrigem.getText().toString();
        String textoDestino = campoDestino.getText().toString();
        String textoData = campoData.getText().toString();
        String textoValorFrete = campoValorFrete.getText().toString();
        String textoPlaca = campoPlaca.getText().toString();
        String textoQuilometragem = campoQuilometragem.getText().toString();

        if (!textoOrigem.isEmpty()) {
            if (!textoDestino.isEmpty()) {
                if (!textoData.isEmpty()) {
                    if (!textoValorFrete.isEmpty()) {
                        if (!textoPlaca.isEmpty()) {
                            if (!textoQuilometragem.isEmpty()) {
                                return true;
                            } else {
                                Toast.makeText(CadastrarViagemActivity.this,
                                        "Quilometragem não preenchida!",
                                        Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        } else {
                            Toast.makeText(CadastrarViagemActivity.this,
                                    "Placa não preenchido!",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(CadastrarViagemActivity.this,
                                "Valor Frete não preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(CadastrarViagemActivity.this,
                            "Data não preenchida",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(CadastrarViagemActivity.this,
                        "Destino não preenchida!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(CadastrarViagemActivity.this,
                    "Origem não preenchido",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
