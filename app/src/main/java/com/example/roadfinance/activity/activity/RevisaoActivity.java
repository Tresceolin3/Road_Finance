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

public class RevisaoActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao, campoPlaca;
    private EditText campoValor;
    private MovimentacaoMecanico movimentacaoMecanico;
    private DatabaseReference firebaseRef = ConfiguraçaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
    private Double revisaoTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisao);

        campoValor = findViewById(R.id.editValorRevisao);
        campoData = findViewById(R.id.editDataRevisao);
        campoCategoria = findViewById(R.id.editCategoriaRevisao);
        campoDescricao = findViewById(R.id.editDescricaoRevisao);
        campoPlaca = findViewById(R.id.editPlaca);

        //Preenche o campo data com a date atual
        campoData.setText(DateUtil.dataAtual());

        recuperarRevisaoTotal();

    }

    public void salvarRevisao(View view) {

        if (validarCamposRevisao()) {
            movimentacaoMecanico = new MovimentacaoMecanico();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacaoMecanico.setValor(valorRecuperado);
            movimentacaoMecanico.setCategoria(campoCategoria.getText().toString());
            movimentacaoMecanico.setDescricao(campoDescricao.getText().toString());
            movimentacaoMecanico.setData(campoData.getText().toString());
            movimentacaoMecanico.setPlaca(campoPlaca.getText().toString());
            movimentacaoMecanico.setTipo("revisao");


            Double revisaoAtualiazada = revisaoTotal + valorRecuperado;
            atualizarRevisao(revisaoAtualiazada);

            movimentacaoMecanico.SalvarMovimentacao(data);
            finish();
        }
    }

    public Boolean validarCamposRevisao() {

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
                            Toast.makeText(RevisaoActivity.this,
                                    "Placa não preenchidas!",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(RevisaoActivity.this,
                                "Descrição não preenchidas!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(RevisaoActivity.this,
                            "Categoria não preenchidas",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(RevisaoActivity.this,
                        "Data não preenchidas!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(RevisaoActivity.this,
                    "Valor não preenchidas",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void recuperarRevisaoTotal() {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                revisaoTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void atualizarRevisao(Double revisao) {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("receitaTotal").setValue(revisao);

    }


}
