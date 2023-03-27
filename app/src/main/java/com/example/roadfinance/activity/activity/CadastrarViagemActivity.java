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
import com.example.roadfinance.activity.model.Viagem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CadastrarViagemActivity extends AppCompatActivity {
    private EditText campoOrigem, campoDestino, campoData, campoValorFrete,
            campoPlaca, campoQuilometragem;
    private Viagem viagem;
    private DatabaseReference firebaseRef = ConfiguraçaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
    private Double valores_viagem;

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

        recuperarValorLiquidoTotal();


    }

    public void SalvarViagem(View view) {
        if (validarCamposViagem()) {
            viagem = new Viagem();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValorFrete.getText().toString());

            viagem.setValorFrete(valorRecuperado);
            viagem.setOrigem(campoOrigem.getText().toString());
            viagem.setDestino(campoDestino.getText().toString());
            viagem.setDataViagem(campoData.getText().toString());
            viagem.setPlaca(campoPlaca.getText().toString());
            viagem.setQuilometragem(campoQuilometragem.getText().toString());

            Double receitaAtualiazada = valorRecuperado;//valores_viagem +
            atualizarValorLiquido(receitaAtualiazada);

            viagem.SalvarViagem(data);
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

    public void recuperarValorLiquidoTotal() {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("viagem").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Viagem viagem = dataSnapshot.getValue(Viagem.class);
                //valores_viagem = viagem.getValor_liquido();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void atualizarValorLiquido(Double valorLiquido) {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("viagem").child(idUsuario);

        usuarioRef.child("valor_liquido").setValue(valorLiquido);

    }



}
