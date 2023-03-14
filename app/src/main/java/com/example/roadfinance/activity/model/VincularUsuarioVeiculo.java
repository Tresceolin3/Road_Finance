package com.example.roadfinance.activity.model;

import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class VincularUsuarioVeiculo {

    private String cpfMotorista, placa;

    public VincularUsuarioVeiculo() {
    }

    public VincularUsuarioVeiculo(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void Salvar() {
        //recuperando email do usuario
        FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        String placa = getPlaca();

        DatabaseReference firebase = ConfiguraçaoFirebase.getFirebaseDatabase();
        firebase.child("caminhao")
                .child(idUsuario)
                .child(placa)
                .push()
                .setValue(this);
    }
}
