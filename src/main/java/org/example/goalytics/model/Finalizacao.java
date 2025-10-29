package org.example.goalytics.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Finalizacao {
    private Integer numEvento;
    private Integer idPartida;
    private Integer idJogador;
    private Boolean sucesso;
    private String parteDoCorpo;
    private Timestamp duracao;
    private Double posicaoXOrigem;
    private Double posicaoYOrigem;
    private Double posicaoZOrigem;
    private Double posicaoXDestino;
    private Double posicaoYDestino;
    private Double posicaoZDestino;
    private String faseDaPartida;
    private Time cronometragem;
    private Integer idEventoAnterior;

    public Finalizacao() {}

    public Finalizacao(Integer numEvento, Integer idPartida, Integer idJogador, Boolean sucesso, String parteDoCorpo, Timestamp duracao, Double posicaoXOrigem, Double posicaoYOrigem, Double posicaoZOrigem, Double posicaoXDestino, Double posicaoYDestino, Double posicaoZDestino, String faseDaPartida, Time cronometragem, Integer idEventoAnterior) {
        this.numEvento = numEvento;
        this.idPartida = idPartida;
        this.idJogador = idJogador;
        this.sucesso = sucesso;
        this.parteDoCorpo = parteDoCorpo;
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

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getParteDoCorpo() {
        return parteDoCorpo;
    }

    public void setParteDoCorpo(String parteDoCorpo) {
        this.parteDoCorpo = parteDoCorpo;
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

    public Time getCronometragem() {
        return cronometragem;
    }

    public void setCronometragem(Time cronometragem) {
        this.cronometragem = cronometragem;
    }

    public Integer getIdEventoAnterior() {
        return idEventoAnterior;
    }

    public void setIdEventoAnterior(Integer idEventoAnterior) {
        this.idEventoAnterior = idEventoAnterior;
    }
}
