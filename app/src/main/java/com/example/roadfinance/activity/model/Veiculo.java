package com.example.roadfinance.activity.model;

import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.example.roadfinance.activity.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Veiculo {
    private String modelo, ano, placa, quilometragem, eixo;

    public Veiculo() {

    }

    public void Salvar() {
        //recuperano email do usuario
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
    //buttonConfirma editEixos


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getEixo() {
        return eixo;
    }

    public void setEixo(String eixo) {
        this.eixo = eixo;
    }
}
