package com.example.roadfinance.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class cadastrar3Activity extends AppCompatActivity {

    private EditText campoSenha, campoEmail, campoSenha2;
    private Button botaoCadastrarFinal;
    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar3);


        campoSenha = findViewById(R.id.editSenha1);
        campoSenha2 = findViewById(R.id.editSenha2);
        campoEmail = findViewById(R.id.editEmail);

        botaoCadastrarFinal = findViewById(R.id.buttonCadastrarSenha);

        botaoCadastrarFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();
                String textSenha2 = campoSenha2.getText().toString();

                //Validar os campos
                if (!textEmail.isEmpty()) {
                    if (!textSenha.isEmpty()) {
                        if (!textSenha2.isEmpty()) {
                            if ((textSenha2.equals(textSenha)) == true) {
                                usuario = new Usuario();
                                usuario.setEmail(textEmail);
                                usuario.setSenha(textSenha);
                                cadastrarUsuario();
                            } else {
                                Toast.makeText(cadastrar3Activity.this,
                                        "Senhas diferentes!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(cadastrar3Activity.this,
                                    "Preencha a senha",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cadastrar3Activity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(cadastrar3Activity.this,
                            "Preencha o E-mail",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void cadastrarUsuario() {
        autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(cadastrar3Activity.this,
                            "Sucesso ao cadastrar o usuário",
                            Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(cadastrar3Activity.this,
                            exececao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}



