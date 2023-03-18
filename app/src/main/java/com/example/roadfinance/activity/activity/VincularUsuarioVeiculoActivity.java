package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.VincularUsuarioVeiculo;

public class VincularUsuarioVeiculoActivity extends AppCompatActivity {

    private EditText campoCpfMotorista, campoPlaca;
    private VincularUsuarioVeiculo vincularUsuarioVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vincular_usuario_veiculo);

        campoCpfMotorista = findViewById(R.id.editCpfMotorista);
        campoPlaca = findViewById(R.id.editPlaca);
    }

    public void salvarVinculoUsuarioVeiculo(View view) {

        if (validarCamposVinculoUsuarioVeiculo()) {
            vincularUsuarioVeiculo = new VincularUsuarioVeiculo();

            vincularUsuarioVeiculo.setCpfMotorista(campoCpfMotorista.getText().toString());
            vincularUsuarioVeiculo.setPlaca(campoPlaca.getText().toString());
            vincularUsuarioVeiculo.Salvar();
            finish();
        }
    }

    public Boolean validarCamposVinculoUsuarioVeiculo() {

        if(!campoCpfMotorista.getText().toString().isEmpty()){
            if(!campoPlaca.getText().toString().isEmpty()){
            }else{
                Toast.makeText(VincularUsuarioVeiculoActivity.this,
                        "CPF não preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(VincularUsuarioVeiculoActivity.this,
                    "Placa não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return null;
    }







}
