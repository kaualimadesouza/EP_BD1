package org.example.goalytics.model;

import java.sql.Timestamp;

public class Defesa {
    private Integer numEvento;
    private Integer idPartida;
    private String tipo;
    private Integer idDefensor;
    private String faseDaPartida;
    private Timestamp cronometragem;
    private Integer idEventoAnterior;

    public Defesa() {}

    public Defesa(Integer numEvento, Integer idPartida, String tipo, Integer idDefensor, String faseDaPartida, Timestamp cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.tipo = tipo;
        this.idDefensor = idDefensor;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdDefensor() {
        return idDefensor;
    }

    public void setIdDefensor(Integer idDefensor) {
        this.idDefensor = idDefensor;
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
