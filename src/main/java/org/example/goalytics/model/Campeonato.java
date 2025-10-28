package org.example.goalytics.model;

public class Campeonato {
    private Integer id;
    private Integer temporada;
    private String tipoCampeonato;
    private String status;
    private String campeao;
    private String nome;

    public Campeonato() {}

    public Campeonato(Integer id, Integer temporada, String tipoCampeonato, String status, String campeao, String nome) {
        this.id = id;
        this.temporada = temporada;
        this.tipoCampeonato = tipoCampeonato;
        this.status = status;
        this.campeao = campeao;
        this.nome = nome;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getTemporada() { return temporada; }
    public void setTemporada(Integer temporada) { this.temporada = temporada; }

    public String getTipoCampeonato() { return tipoCampeonato; }
    public void setTipoCampeonato(String tipoCampeonato) { this.tipoCampeonato = tipoCampeonato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCampeao() { return campeao; }
    public void setCampeao(String campeao) { this.campeao = campeao; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}

