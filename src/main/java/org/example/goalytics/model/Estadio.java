package org.example.goalytics.model;

import java.util.Date;

// SELECT id, nome_oficial, nome_apelido, capacidade_atual, capacidade_maxima, pais, endereco, data_inauguracao, tipo_gramado
//FROM public.estadio;
public class Estadio {
    private Integer id;
    private String nomeOficial;
    private String nomeApelido;
    private Integer capacidadeAtual;
    private Integer capacidadeMaxima;
    private String pais;
    private String endereco;
    private Date dataInauguracao;
    private String tipoGramado;

    // Construtor padrão necessário para serialização JSON
    public Estadio() {}

    public Estadio(Integer id, String nomeOficial, String nomeApelido, Integer capacidadeAtual, Integer capacidadeMaxima, String pais, String endereco, Date dataInauguracao, String tipoGramado) {
        this.id = id;
        this.nomeOficial = nomeOficial;
        this.nomeApelido = nomeApelido;
        this.capacidadeAtual = capacidadeAtual;
        this.capacidadeMaxima = capacidadeMaxima;
        this.pais = pais;
        this.endereco = endereco;
        this.dataInauguracao = dataInauguracao;
        this.tipoGramado = tipoGramado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    public String getNomeApelido() {
        return nomeApelido;
    }

    public void setNomeApelido(String nomeApelido) {
        this.nomeApelido = nomeApelido;
    }

    public Integer getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public void setCapacidadeAtual(Integer capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataInauguracao() {
        return dataInauguracao;
    }

    public void setDataInauguracao(Date dataInauguracao) {
        this.dataInauguracao = dataInauguracao;
    }

    public String getTipoGramado() {
        return tipoGramado;
    }

    public void setTipoGramado(String tipoGramado) {
        this.tipoGramado = tipoGramado;
    }
}
