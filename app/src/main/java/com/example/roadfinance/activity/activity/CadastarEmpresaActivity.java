package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.model.Empresa;

public class CadastarEmpresaActivity extends AppCompatActivity {

    private EditText campoCnpj, campoNomeEmpresa, campoDataAbertura, campoCelular;
    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastar_empresa);

        campoCnpj = findViewById(R.id.editCNPJ);
        campoNomeEmpresa = findViewById(R.id.editNomeEmpresa);
        campoDataAbertura = findViewById(R.id.editDataAbertura);
        campoCelular = findViewById(R.id.editCelular);
    }

    public void salvarEmpresa(View view) {

        if (validarCamposEmpresa()) {
            empresa = new Empresa();

            empresa.setNomeEmpresa(campoNomeEmpresa.getText().toString());
            empresa.setCnpj(campoCnpj.getText().toString());
            empresa.setDataAbertura(campoDataAbertura.getText().toString());
            empresa.setCelular(campoCelular.getText().toString());
            empresa.Salvar();
            finish();
        }
    }

    public Boolean validarCamposEmpresa() {

        if(!campoNomeEmpresa.getText().toString().isEmpty()){
            if(!campoCnpj.getText().toString().isEmpty()){
                if(!campoDataAbertura.getText().toString().isEmpty()){
                    if(!campoCelular.getText().toString().isEmpty()){
                        return true;
                    }else{
                        Toast.makeText(CadastarEmpresaActivity.this,
                                "Nome não preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(CadastarEmpresaActivity.this,
                            "CNPJ não preenchido!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(CadastarEmpresaActivity.this,
                        "Data de abertura não preenchida!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(CadastarEmpresaActivity.this,
                    "Celular não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
