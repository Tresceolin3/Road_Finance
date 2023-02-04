package com.example.roadfinance.activity.helper;

import android.app.Application;



public class MyApplication extends Application {
    private static MyApplication instance;
    private String nome;
    private String sobre_nome;
    private String cpf;
    private String data_nas;
    private String celular;
    private String cep,cidade,rua,n_rua,bairro;


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSobreNome() {
        return getSobre_nome();
    }

    public void setSobre_nome(String sobre_nome) {
        this.sobre_nome = sobre_nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNas() {
        return getData_nas();
    }

    public void setData_nas(String data_nas) {
        this.data_nas = data_nas;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSobre_nome() {
        return sobre_nome;
    }

    public String getData_nas() {
        return data_nas;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNRua() {
        return n_rua;
    }

    public void setNrua(String n_rua) {
        this.n_rua = n_rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}




