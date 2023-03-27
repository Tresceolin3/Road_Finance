package com.example.roadfinance.activity.model;

import com.example.roadfinance.activity.config.ConfiguraçaoFirebase;
import com.example.roadfinance.activity.helper.Base64Custom;
import com.example.roadfinance.activity.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Viagem {

    private String origem;
    private String destino;
    private String data_viagem;
    private String placa;
    private String quilometragem;
    private Double valor_frete;
    private String key;
    private Double valor_liquido;

    public Viagem() {

    }

    public void SalvarViagem(String dataSelecionada) {
        FirebaseAuth autenticacao = ConfiguraçaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        String mesAno = DateUtil.mesAnoDataEscolhida(dataSelecionada);

        DatabaseReference firebase = ConfiguraçaoFirebase.getFirebaseDatabase();
        firebase.child("viagem")
                .child(idUsuario)
                .child(mesAno)
                .push()
                .setValue(this);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataViagem() {
        return data_viagem;
    }

    public void setDataViagem(String data_viagem) {
        this.data_viagem = data_viagem;
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

    public Double getValorFrete() {
        return valor_frete;
    }

    public void setValorFrete(Double valor_frete) {
        this.valor_frete = valor_frete;
    }


    public Double getValor_liquido() {
        return valor_liquido;
    }

    public void setValor_liquido(Double valor_liquido) {
        this.valor_liquido = valor_liquido;
    }
}
