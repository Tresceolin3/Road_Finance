package com.example.roadfinance.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roadfinance.R;
import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.example.roadfinance.activity.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    // private FirebaseAuth autenticacao;
    private FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguraçaoFirebase.getFirebaseDatabase();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .canGoBackward(true)
                .build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioVerificado();

    }


    public void btEntrar(View view) {
        startActivity(new Intent(this, loginActivity.class));

    }

    public void btCadastrar(View view) {
        startActivity(new Intent(this, CategoriaUsuarioActivity.class));
    }


    public void verificarUsuarioVerificado() {
        autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        if (autenticacao.getCurrentUser() != null) {
            //abrirTelaPrincipal();
            verificaTela();

        }
    }


    public void verificaTela() {
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        if (usuarioRef != null) {
            usuarioRef.child("categoria").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String categoria = dataSnapshot.getValue(String.class);
                        switch (categoria) {
                            case "Motorista":
                                abrirTelaMotorista();
                                break;
                            case "Proprietario":
                                abrirTelaProprietario();
                                break;
                            case "Mecanico":
                                abrirTelaMecanico();
                                break;
                            default:
                                // categoria inválida, tratar erro
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // tratamento de erro
                }
            });
        } else {
            startActivity(new Intent(this, CategoriaUsuarioActivity.class));
        }
    }


    public void abrirTelaMotorista() {
        startActivity(new Intent(this, MecanicoActivity.class));
    }

    public void abrirTelaMecanico() {
        startActivity(new Intent(this, MecanicoActivity.class));
    }

    public void abrirTelaProprietario() {
        startActivity(new Intent(this, PrincipalActivity.class));
    }


}
