package com.example.roadfinance.activity.model;

public class Endereco {
    private String cep,cidade,rua,n_rua,bairro;

    public Endereco(){

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

    public String getN_rua() {
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
