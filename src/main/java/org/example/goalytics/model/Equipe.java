package org.example.goalytics.model;

import java.util.Date;

public class Equipe {
    private Integer id;
    private String estado;
    private String cidade;
    private String nomePopular;
    private String nomeOficial;
    private Date dataFuncacao;
    private String sigla;
    private String nomeEstadio;

    public Equipe() {}

    public Equipe(Integer id, String estado, String cidade, String nomePopular, String nomeOficial, Date dataFuncacao, String sigla, String nomeEstadio) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.nomePopular = nomePopular;
        this.nomeOficial = nomeOficial;
        this.dataFuncacao = dataFuncacao;
        this.sigla = sigla;
        this.nomeEstadio = nomeEstadio;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getNomePopular() { return nomePopular; }
    public void setNomePopular(String nomePopular) { this.nomePopular = nomePopular; }

    public String getNomeOficial() { return nomeOficial; }
    public void setNomeOficial(String nomeOficial) { this.nomeOficial = nomeOficial; }

    public Date getDataFuncacao() { return dataFuncacao; }
    public void setDataFuncacao(Date dataFuncacao) { this.dataFuncacao = dataFuncacao; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public String getNomeEstadio() { return nomeEstadio; }
    public void setNomeEstadio(String nomeEstadio) { this.nomeEstadio = nomeEstadio; }
}

