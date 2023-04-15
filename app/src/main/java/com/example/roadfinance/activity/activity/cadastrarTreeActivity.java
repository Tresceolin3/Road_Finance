package com.example.roadfinance.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.example.roadfinance.activity.helper.MyApplication;
import com.example.roadfinance.activity.model.Endereco;
import com.example.roadfinance.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class cadastrarTreeActivity extends AppCompatActivity {

    private EditText campoSenha, campoEmail, campoSenhaTwo;
    private Button botaoCadastrarFinal;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private Endereco endereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tree);

        //nomear a tullbar
        //getSupportActionBar().setTitle("Cadastro");

        campoSenha = findViewById(R.id.editSenha1);
        campoSenhaTwo = findViewById(R.id.editSenha2);
        campoEmail = findViewById(R.id.editEmail);

        botaoCadastrarFinal = findViewById(R.id.buttonCadastrarSenha);
        botaoCadastrarFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();
                String textSenhaV = campoSenhaTwo.getText().toString();

                //Validar os campos
                if (!textEmail.isEmpty()) {
                    if (!textSenha.isEmpty()) {
                        if (!textSenhaV.isEmpty()) {
                            if ((textSenhaV.equals(textSenha)) == true) {
                                usuario = new Usuario();

                                usuario.setNome(MyApplication.getInstance().getNome());
                                usuario.setSobre_nome(MyApplication.getInstance().getSobreNome());
                                usuario.setCpf(MyApplication.getInstance().getCpf());
                                usuario.setData_nasc(MyApplication.getInstance().getDataNas());
                                usuario.setCelular(MyApplication.getInstance().getCelular());
                                usuario.setCategoria(MyApplication.getInstance().getCategoria());

                                endereco = new Endereco();
                                endereco.setCep(MyApplication.getInstance().getCep());
                                endereco.setCidade(MyApplication.getInstance().getCidade());
                                endereco.setRua(MyApplication.getInstance().getRua());
                                endereco.setNrua(MyApplication.getInstance().getNRua());
                                endereco.setBairro(MyApplication.getInstance().getBairro());
                                usuario.setEndereco(endereco);

                                usuario.setEmail(textEmail);
                                usuario.setSenha(textSenha);
                                cadastrarUsuario();
                            } else {
                                Toast.makeText(cadastrarTreeActivity.this,
                                        "Senhas diferentes!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(cadastrarTreeActivity.this,
                                    "Preencha a senha",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cadastrarTreeActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(cadastrarTreeActivity.this,
                            "Preencha o E-mail",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void verificarUsuarioVerificado() {
        autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        if (autenticacao.getCurrentUser() != null) {
            if (MyApplication.getInstance().getCategoria().equals("Proprietario")) {
                startActivity(new Intent(this, ProprietarioActivity.class));
            } else if (MyApplication.getInstance().getCategoria().equals("Mecanico")) {
                startActivity(new Intent(this, MecanicoActivity.class));
            } else if (MyApplication.getInstance().getCategoria().equals("Motorista")) {
                startActivity(new Intent(this, MotoristaActivity.class));
            }

        }
    }

    public void cadastrarUsuario() {

        autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();
                    verificarUsuarioVerificado();
                    finish();
                    //onStart();

                } else {
                    String exececao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        exececao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exececao = "Por favor digite e-mail valido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        exececao = "Esta consta já foi cadastrada";
                    } catch (Exception e) {
                        exececao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(cadastrarTreeActivity.this,
                            exececao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}



