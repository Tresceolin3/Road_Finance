package com.example.roadfinance.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.example.roadfinance.activity.helper.DateUtil;
import com.example.roadfinance.activity.model.MovimentacaoMecanico;
import com.example.roadfinance.activity.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ManuntencaoActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao, campoPlaca;
    private EditText campoValor;
    private MovimentacaoMecanico movimentacaoMecanico;
    private DatabaseReference firebaseRef = ConfiguraçaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
    private Double manuntencaoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuntencao);

        campoValor = findViewById(R.id.editValorManuntencao);
        campoData = findViewById(R.id.editDataManuntencao);
        campoCategoria = findViewById(R.id.editCategoriaManuntencao);
        campoDescricao = findViewById(R.id.editDescricaoManuntencao);
        campoPlaca = findViewById(R.id.editPlacaManuntencao);

        //Preenche o campo data com a date atual
        campoData.setText(DateUtil.dataAtual());

        recuperarManuntencaoTotal();
    }

    public void salvarManuntencao(View view) {

        if (validarCamposManuntencao()) {
            movimentacaoMecanico = new MovimentacaoMecanico();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacaoMecanico.setValor(valorRecuperado);
            movimentacaoMecanico.setCategoria(campoCategoria.getText().toString());
            movimentacaoMecanico.setDescricao(campoDescricao.getText().toString());
            movimentacaoMecanico.setData(campoData.getText().toString());
            movimentacaoMecanico.setPlaca(campoPlaca.getText().toString());
            movimentacaoMecanico.setTipo("manuntencao");


            Double manuntencaoAtualiazada = manuntencaoTotal + valorRecuperado;
            atualizarManuntencao(manuntencaoAtualiazada);

            movimentacaoMecanico.SalvarMovimentacao(data);
            finish();
        }
    }

    public Boolean validarCamposManuntencao() {

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();
        String textoPlaca = campoPlaca.getText().toString();


        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()) {
                if (!textoCategoria.isEmpty()) {
                    if (!textoDescricao.isEmpty()) {
                        if (!textoPlaca.isEmpty()) {
                            return true;
                        } else {
                            Toast.makeText(ManuntencaoActivity.this,
                                    "Placa não preenchidas!",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(ManuntencaoActivity.this,
                                "Descrição não preenchidas!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(ManuntencaoActivity.this,
                            "Categoria não preenchidas",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(ManuntencaoActivity.this,
                        "Data não preenchidas!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(ManuntencaoActivity.this,
                    "Valor não preenchidas",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void recuperarManuntencaoTotal() {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                manuntencaoTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void atualizarManuntencao(Double manuntencao) {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(manuntencao);

    }

}
