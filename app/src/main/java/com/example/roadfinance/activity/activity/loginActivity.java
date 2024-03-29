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
import com.example.roadfinance.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

public class loginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button buttonEnviar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private DatabaseReference firebaseRef = ConfiguraçaoFirebase.getFirebaseDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail2);
        campoSenha = findViewById(R.id.editSenha2);
        buttonEnviar = findViewById(R.id.buttonEntrar2);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if (!textEmail.isEmpty()) {
                    if (!textSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textEmail);
                        usuario.setSenha(textSenha);
                        validarLogin();
                    } else {
                        Toast.makeText(loginActivity.this,
                                "Preencha a senha",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginActivity.this,
                            "Preencha o e-mail",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String recuperarCategoria() {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);
        return usuarioRef.child("categoria").toString();
    }


    public void validarLogin() {
        autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaTipo();
                } else {
                    String exececao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        exececao = "Usuário não cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exececao = "E-mail e senha não correspondem" +
                                " a um usuário cadastrado";
                    } catch (Exception e) {
                        exececao = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(loginActivity.this,
                            exececao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaTipo() {
        if (recuperarCategoria().equals("Proprietario")) {
            startActivity(new Intent(this, ProprietarioActivity.class));
        } else if (recuperarCategoria().equals("Mecanico")) {
            startActivity(new Intent(this, MotoristaActivity.class));
        } else if (recuperarCategoria().equals("Motorista")) {

        } else {
            System.out.println("Erro ao abrir!");
        }
        finish();
    }

}
