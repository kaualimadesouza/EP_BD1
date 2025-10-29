package org.example.goalytics.model;

import java.sql.Timestamp;

public class Falta {
    private Integer numEvento;
    private Integer idPartida;
    private Integer idJogadorVitima;
    private Integer idJogadorInfrator;
    private String tipo;
    private String local;
    private String faseDaPartida;
    private Timestamp cronometragem;
    private Integer idEventoAnterior;

    public Falta() {}

    public Falta(Integer numEvento, Integer idPartida, Integer idJogadorVitima, Integer idJogadorInfrator, String tipo, String local, String faseDaPartida, Timestamp cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.idJogadorVitima = idJogadorVitima;
        this.idJogadorInfrator = idJogadorInfrator;
        this.tipo = tipo;
        this.local = local;
        this.faseDaPartida = faseDaPartida;
        this.cronometragem = cronometragem;
        this.idEventoAnterior = idEventoAnterior;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdJogadorVitima() {
        return idJogadorVitima;
    }

    public void setIdJogadorVitima(Integer idJogadorVitima) {
        this.idJogadorVitima = idJogadorVitima;
    }

    public Integer getIdJogadorInfrator() {
        return idJogadorInfrator;
    }

    public void setIdJogadorInfrator(Integer idJogadorInfrator) {
        this.idJogadorInfrator = idJogadorInfrator;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getFaseDaPartida() {
        return faseDaPartida;
    }

    public void setFaseDaPartida(String faseDaPartida) {
        this.faseDaPartida = faseDaPartida;
    }

    public Timestamp getCronometragem() {
        return cronometragem;
    }

    public void setCronometragem(Timestamp cronometragem) {
        this.cronometragem = cronometragem;
    }

    public Integer getIdEventoAnterior() {
        return idEventoAnterior;
    }

    public void setIdEventoAnterior(Integer idEventoAnterior) {
        this.idEventoAnterior = idEventoAnterior;
    }
}
