package com.example.roadfinance.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.Veiculo;

public class CadastrarVeiculoActivity extends AppCompatActivity {

    private EditText campoModelo, campoAno, campoPlaca, campoQuilometragrem, campoEixo;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_veiculo);

        campoModelo = findViewById(R.id.editModelo);
        campoAno = findViewById(R.id.editAno);
        campoPlaca = findViewById(R.id.editPlaca);
        campoQuilometragrem = findViewById(R.id.editQuilometragem);
        campoEixo = findViewById(R.id.editEixos);

        //Preenche o campo data com a date atual

        //recuperando receitas total


    }

    public void salvarCaminhao(View view) {

        if (validarCamposVeiculo()) {
            veiculo = new Veiculo();

            veiculo.setModelo(campoModelo.getText().toString());
            veiculo.setAno(campoAno.getText().toString());
            veiculo.setPlaca(campoPlaca.getText().toString());
            veiculo.setQuilometragem(campoQuilometragrem.getText().toString());
            veiculo.setEixo(campoEixo.getText().toString());
            veiculo.Salvar();
            finish();
        }
    }


    public Boolean validarCamposVeiculo() {

        if (!campoModelo.getText().toString().isEmpty()) {
            if (!campoAno.getText().toString().isEmpty()) {
                if (!campoPlaca.getText().toString().isEmpty()) {
                    if (!campoQuilometragrem.getText().toString().isEmpty()) {
                        if (!campoEixo.getText().toString().isEmpty()) {
                            return true;
                        } else {
                            Toast.makeText(CadastrarVeiculoActivity.this,
                                    "Eixo não preenchido!",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(CadastrarVeiculoActivity.this,
                                "Quilometragem não preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(CadastrarVeiculoActivity.this,
                            "Placa não preenchida!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(CadastrarVeiculoActivity.this,
                        "Ano não preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(CadastrarVeiculoActivity.this,
                    "Modelo não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
