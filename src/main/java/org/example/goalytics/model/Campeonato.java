package org.example.goalytics.model;

public class Campeonato {
    private Integer id;
    private Integer temporada;
    private String tipoCampeonato;
    private String status;
    private String campeao;
    private String nome;
    private String urlCampeonato;
    private String regiao;

    public Campeonato(Integer id, Integer temporada, String tipoCampeonato, String status, String campeao, String nome, String urlCampeonato, String regiao) {
        this.id = id;
        this.temporada = temporada;
        this.tipoCampeonato = tipoCampeonato;
        this.status = status;
        this.campeao = campeao;
        this.nome = nome;
        this.urlCampeonato = urlCampeonato;
        this.regiao = regiao;
    }

    public String getUrlCampeonato() {
        return urlCampeonato;
    }

    public void setUrlCampeonato(String urlCampeonato) {
        this.urlCampeonato = urlCampeonato;
    }

    public String getRegiao() {
        return regiao;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getTemporada() { return temporada; }

    public String getTipoCampeonato() { return tipoCampeonato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCampeao() { return campeao; }

    public String getNome() { return nome; }
}


