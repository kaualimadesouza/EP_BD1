package org.example.goalytics.model;

import java.util.Date;

public class Arbitro {
    private Integer id;
    private String federacao;
    private String nomeCompleto;
    private Date dataNascimento;

    public Arbitro() {}

    public Arbitro(Integer id, String federacao, String nomeCompleto, Date dataNascimento) {
        this.id = id;
        this.federacao = federacao;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFederacao() {
        return federacao;
    }

    public void setFederacao(String federacao) {
        this.federacao = federacao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

