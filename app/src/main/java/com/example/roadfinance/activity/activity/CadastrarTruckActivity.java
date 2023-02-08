package com.example.roadfinance.activity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.Caminhao;

public class CadastrarTruckActivity extends AppCompatActivity {

    private EditText campoModelo, campoAno, campoPlaca, campoQuilometragrem, campoEixo;
    private Caminhao caminhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_truck);

        campoModelo = findViewById(R.id.editModelo);
        campoAno = findViewById(R.id.editAno);
        campoPlaca = findViewById(R.id.editPlaca);
        campoQuilometragrem = findViewById(R.id.editQuilometragem);
        campoEixo = findViewById(R.id.editEixos);

        //Preenche o campo data com a date atual

        //recuperando receitas total


    }

    public void salvarCaminhao(View view) {

        if (validarCamposTruck()) {
            caminhao = new Caminhao();

            caminhao.setModelo(campoModelo.getText().toString());
            caminhao.setAno(campoAno.getText().toString());
            caminhao.setPlaca(campoPlaca.getText().toString());
            caminhao.setQuilometragem(campoQuilometragrem.getText().toString());
            caminhao.setEixo(campoEixo.getText().toString());
            caminhao.Salvar();
            finish();
        }
    }


    public Boolean validarCamposTruck() {

        if (!campoModelo.getText().toString().isEmpty()) {
            if (!campoAno.getText().toString().isEmpty()) {
                if (!campoPlaca.getText().toString().isEmpty()) {
                    if (!campoQuilometragrem.getText().toString().isEmpty()) {
                        if (!campoEixo.getText().toString().isEmpty()) {
                            return true;
                        } else {
                            Toast.makeText(CadastrarTruckActivity.this,
                                    "Eixo não preenchido!",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(CadastrarTruckActivity.this,
                                "Quilometragem não preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(CadastrarTruckActivity.this,
                            "Placa não preenchida!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(CadastrarTruckActivity.this,
                        "Ano não preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(CadastrarTruckActivity.this,
                    "Modelo não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
