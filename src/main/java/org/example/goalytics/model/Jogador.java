package org.example.goalytics.model;

import java.math.BigDecimal;
import java.util.Date;

public class Jogador {
    private Integer id;
    private String posicao;
    private String peDominante;
    private BigDecimal altura;
    private BigDecimal preco;
    private Integer numeroCamisa;
    private String nomeCompleto;
    private Date dataNascimento;
    private String nacionalidade;
    private String nomePopular;

    public Jogador() {}

    public Jogador(Integer id, String posicao, String peDominante, BigDecimal altura, BigDecimal preco, Integer numeroCamisa, String nomeCompleto, Date dataNascimento, String nacionalidade, String nomePopular) {
        this.id = id;
        this.posicao = posicao;
        this.peDominante = peDominante;
        this.altura = altura;
        this.preco = preco;
        this.numeroCamisa = numeroCamisa;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.nomePopular = nomePopular;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public String getPeDominante() { return peDominante; }
    public void setPeDominante(String peDominante) { this.peDominante = peDominante; }

    public BigDecimal getAltura() { return altura; }
    public void setAltura(BigDecimal altura) { this.altura = altura; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getNumeroCamisa() { return numeroCamisa; }
    public void setNumeroCamisa(Integer numeroCamisa) { this.numeroCamisa = numeroCamisa; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getNomePopular() { return nomePopular; }
    public void setNomePopular(String nomePopular) { this.nomePopular = nomePopular; }
}
