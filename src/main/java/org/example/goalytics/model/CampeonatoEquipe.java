package org.example.goalytics.model;

public class CampeonatoEquipe {
    private Integer id;
    private Integer idCampeonato;
    private Integer idEquipe;
    private String colocacaoOuFase;

    public CampeonatoEquipe() {}

    public CampeonatoEquipe(Integer id, Integer idCampeonato, Integer idEquipe, String colocacaoOuFase) {
        this.id = id;
        this.idCampeonato = idCampeonato;
        this.idEquipe = idEquipe;
        this.colocacaoOuFase = colocacaoOuFase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(Integer idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getColocacaoOuFase() {
        return colocacaoOuFase;
    }

    public void setColocacaoOuFase(String colocacaoOuFase) {
        this.colocacaoOuFase = colocacaoOuFase;
    }
}
