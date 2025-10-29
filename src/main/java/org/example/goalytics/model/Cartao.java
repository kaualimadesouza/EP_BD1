package org.example.goalytics.model;

import java.sql.Timestamp;

public class Cartao {
    private Integer numEvento;
    private Integer idPartida;
    private Integer idPessoa;
    private String tipo;
    private String faseDaPartida;
    private Timestamp cronometro;
    private Integer idEventoAnterior;

    public Cartao() {}

    public Cartao(Integer numEvento, Integer idPartida, Integer idPessoa, String tipo, String faseDaPartida, Timestamp cronometro, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.idPessoa = idPessoa;
        this.tipo = tipo;
        this.faseDaPartida = faseDaPartida;
        this.cronometro = cronometro;
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

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFaseDaPartida() {
        return faseDaPartida;
    }

    public void setFaseDaPartida(String faseDaPartida) {
        this.faseDaPartida = faseDaPartida;
    }

    public Timestamp getCronometro() {
        return cronometro;
    }

    public void setCronometro(Timestamp cronometro) {
        this.cronometro = cronometro;
    }

    public Integer getIdEventoAnterior() {
        return idEventoAnterior;
    }

    public void setIdEventoAnterior(Integer idEventoAnterior) {
        this.idEventoAnterior = idEventoAnterior;
    }
}
