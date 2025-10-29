package org.example.goalytics.model;

import java.sql.Timestamp;

public class Passe {
    private Integer numEvento;
    private Integer idPartida;
    private Integer idJogadorOrigem;
    private Integer idJogadorDestino;
    private Boolean sucesso;
    private Timestamp duracao;
    private Double posicaoXOrigem;
    private Double posicaoYOrigem;
    private Double posicaoZOrigem;
    private Double posicaoXDestino;
    private Double posicaoYDestino;
    private Double posicaoZDestino;
    private String faseDaPartida;
    private Timestamp cronometragem;
    private Integer idEventoAnterior;

    public Passe() {}

    public Passe(Integer numEvento, Integer idPartida, Integer idJogadorOrigem, Integer idJogadorDestino, Boolean sucesso, Timestamp duracao, Double posicaoXOrigem, Double posicaoYOrigem, Double posicaoZOrigem, Double posicaoXDestino, Double posicaoYDestino, Double posicaoZDestino, String faseDaPartida, Timestamp cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.idJogadorOrigem = idJogadorOrigem;
        this.idJogadorDestino = idJogadorDestino;
        this.sucesso = sucesso;
        this.duracao = duracao;
        this.posicaoXOrigem = posicaoXOrigem;
        this.posicaoYOrigem = posicaoYOrigem;
        this.posicaoZOrigem = posicaoZOrigem;
        this.posicaoXDestino = posicaoXDestino;
        this.posicaoYDestino = posicaoYDestino;
        this.posicaoZDestino = posicaoZDestino;
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

    public Integer getIdJogadorOrigem() {
        return idJogadorOrigem;
    }

    public void setIdJogadorOrigem(Integer idJogadorOrigem) {
        this.idJogadorOrigem = idJogadorOrigem;
    }

    public Integer getIdJogadorDestino() {
        return idJogadorDestino;
    }

    public void setIdJogadorDestino(Integer idJogadorDestino) {
        this.idJogadorDestino = idJogadorDestino;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Timestamp getDuracao() {
        return duracao;
    }

    public void setDuracao(Timestamp duracao) {
        this.duracao = duracao;
    }

    public Double getPosicaoXOrigem() {
        return posicaoXOrigem;
    }

    public void setPosicaoXOrigem(Double posicaoXOrigem) {
        this.posicaoXOrigem = posicaoXOrigem;
    }

    public Double getPosicaoYOrigem() {
        return posicaoYOrigem;
    }

    public void setPosicaoYOrigem(Double posicaoYOrigem) {
        this.posicaoYOrigem = posicaoYOrigem;
    }

    public Double getPosicaoZOrigem() {
        return posicaoZOrigem;
    }

    public void setPosicaoZOrigem(Double posicaoZOrigem) {
        this.posicaoZOrigem = posicaoZOrigem;
    }

    public Double getPosicaoXDestino() {
        return posicaoXDestino;
    }

    public void setPosicaoXDestino(Double posicaoXDestino) {
        this.posicaoXDestino = posicaoXDestino;
    }

    public Double getPosicaoYDestino() {
        return posicaoYDestino;
    }

    public void setPosicaoYDestino(Double posicaoYDestino) {
        this.posicaoYDestino = posicaoYDestino;
    }

    public Double getPosicaoZDestino() {
        return posicaoZDestino;
    }

    public void setPosicaoZDestino(Double posicaoZDestino) {
        this.posicaoZDestino = posicaoZDestino;
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
