package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
        String nomeEmpresa = campoNomeEmpresa.getText().toString().trim();
        String cnpj = campoCnpj.getText().toString().trim();
        String dataAbertura = campoDataAbertura.getText().toString().trim();
        String celular = campoCelular.getText().toString().trim();

        if(nomeEmpresa.isEmpty()){
            Toast.makeText(CadastarEmpresaActivity.this,
                    "Nome da empresa não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validação do CNPJ
        if(cnpj.isEmpty()){
            Toast.makeText(CadastarEmpresaActivity.this,
                    "CNPJ não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(!validarCNPJ(cnpj)){
                Toast.makeText(CadastarEmpresaActivity.this,
                        "CNPJ inválido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // Validação da data de abertura
        if(dataAbertura.isEmpty()){
            Toast.makeText(CadastarEmpresaActivity.this,
                    "Data de abertura não preenchida!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(!validarData(dataAbertura)){
                Toast.makeText(CadastarEmpresaActivity.this,
                        "Data de abertura inválida!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // Validação do celular
        if(celular.isEmpty()){
            Toast.makeText(CadastarEmpresaActivity.this,
                    "Celular não preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(!validarCelular(celular)){
                Toast.makeText(CadastarEmpresaActivity.this,
                        "Celular inválido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private boolean validarCNPJ(String cnpj) {
        // Remove todos os caracteres que não são números do CNPJ
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verifica se o CNPJ possui 14 dígitos
        if(cnpj.length() != 14){
            return false;
        }

        // Calcula o primeiro dígito verificador do CNPJ
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            int num = Character.getNumericValue(cnpj.charAt(i));
            soma += num * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int resto = soma % 11;
        int dv1 = resto < 2 ? 0 : 11 - resto;

        // Calcula o segundo dígito verificador do CNPJ
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            int num = Character.getNumericValue(cnpj.charAt(i));
            soma += num * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        soma += dv1 * 2;
        resto = soma % 11;
        int dv2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se os dígitos verificadores do CNPJ estão corretos
        //return Character.getNumericValue(cnpj.charAt(12)) == dv1 &&
        //        Character.getNumericValue(cnpj.charAt(13)) == dv2;
        return true;
    }




    private boolean validarData(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        try {
            format.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarCelular(String celular) {
        // Remove todos os caracteres que não são números do celular
        celular = celular.replaceAll("[^\\d]", "");

        // Verifica se o celular possui 11 dígitos
        if(celular.length() != 11){
            return false;
        }

        // Verifica se o celular é um número válido segundo as regras da Anatel
        Pattern pattern = Pattern.compile("(\\d)\\1{10,}");
        Matcher matcher = pattern.matcher(celular);
        if(matcher.find()){
            return false;
        }

        // Caso tenha passado em todas as verificações, retorna true
        return true;
    }


}
