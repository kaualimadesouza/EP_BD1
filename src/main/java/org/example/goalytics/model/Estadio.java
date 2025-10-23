package org.example.goalytics.model;

import java.util.Date;

// SELECT id, nome_oficial, nome_apelido, capacidade_atual, capacidade_maxima, pais, endereco, data_inauguracao, tipo_gramado
//FROM public.estadio;
public class Estadio {
    private Integer id;
    private String nome_oficial;
    private String nome_apelido;
    private Integer capacidade_atual;
    private Integer capacidade_maxima;
    private String pais;
    private String endereco;
    private Date data_inauguracao;
    private String tipo_gramado;

    // Construtor padrão necessário para serialização JSON
    public Estadio() {}

    public Estadio(Integer id, String nome_oficial, String nome_apelido, Integer capacidade_atual, Integer capacidade_maxima, String pais, String endereco, Date data_inauguracao, String tipo_gramado) {
        this.id = id;
        this.nome_oficial = nome_oficial;
        this.nome_apelido = nome_apelido;
        this.capacidade_atual = capacidade_atual;
        this.capacidade_maxima = capacidade_maxima;
        this.pais = pais;
        this.endereco = endereco;
        this.data_inauguracao = data_inauguracao;
        this.tipo_gramado = tipo_gramado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_oficial() {
        return nome_oficial;
    }

    public void setNome_oficial(String nome_oficial) {
        this.nome_oficial = nome_oficial;
    }

    public String getNome_apelido() {
        return nome_apelido;
    }

    public void setNome_apelido(String nome_apelido) {
        this.nome_apelido = nome_apelido;
    }

    public Integer getCapacidade_atual() {
        return capacidade_atual;
    }

    public void setCapacidade_atual(Integer capacidade_atual) {
        this.capacidade_atual = capacidade_atual;
    }

    public Integer getCapacidade_maxima() {
        return capacidade_maxima;
    }

    public void setCapacidade_maxima(Integer capacidade_maxima) {
        this.capacidade_maxima = capacidade_maxima;
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

    public Date getData_inauguracao() {
        return data_inauguracao;
    }

    public void setData_inauguracao(Date data_inauguracao) {
        this.data_inauguracao = data_inauguracao;
    }

    public String getTipo_gramado() {
        return tipo_gramado;
    }

    public void setTipo_gramado(String tipo_gramado) {
        this.tipo_gramado = tipo_gramado;
    }
}
