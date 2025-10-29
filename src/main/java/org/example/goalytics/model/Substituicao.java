package org.example.goalytics.model;

import java.sql.Timestamp;

public class Substituicao {
    private Integer numEvento;
    private Integer idPartida;
    private Integer idPessoaSai;
    private Integer idPessoaEntra;
    private String faseDaPartida;
    private Timestamp cronometragem;
    private Integer idEventoAnterior;

    public Substituicao() {}

    public Substituicao(Integer numEvento, Integer idPartida, Integer idPessoaSai, Integer idPessoaEntra, String faseDaPartida, Timestamp cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.idPessoaSai = idPessoaSai;
        this.idPessoaEntra = idPessoaEntra;
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

    public Integer getIdPessoaSai() {
        return idPessoaSai;
    }

    public void setIdPessoaSai(Integer idPessoaSai) {
        this.idPessoaSai = idPessoaSai;
    }

    public Integer getIdPessoaEntra() {
        return idPessoaEntra;
    }

    public void setIdPessoaEntra(Integer idPessoaEntra) {
        this.idPessoaEntra = idPessoaEntra;
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
