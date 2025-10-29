package org.example.goalytics.model;

import java.sql.Timestamp;

public class Escanteio {
    private Integer numEvento;
    private Integer idPartida;
    private String lado;
    private String faseDaPartida;
    private Timestamp cronometragem;
    private Integer idEventoAnterior;

    public Escanteio() {}

    public Escanteio(Integer numEvento, Integer idPartida, String lado, String faseDaPartida, Timestamp cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.lado = lado;
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

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
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
