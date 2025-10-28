package org.example.goalytics.model;

import java.util.Date;

public class Tecnico {
    private Integer id;
    private String formacaoFav;
    private String nomeCompleto;
    private Date dataNascimento;
    private String nacionalidade;

    public Tecnico() {}

    public Tecnico(Integer id, String formacaoFav, String nomeCompleto, Date dataNascimento, String nacionalidade) {
        this.id = id;
        this.formacaoFav = formacaoFav;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFormacaoFav() { return formacaoFav; }
    public void setFormacaoFav(String formacaoFav) { this.formacaoFav = formacaoFav; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
}

