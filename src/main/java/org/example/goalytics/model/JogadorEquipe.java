package org.example.goalytics.model;

import java.util.Date;

public class JogadorEquipe {
    private Integer id;
    private Integer idEquipe;
    private Integer idJogador;
    private Date dataInicioContrato;
    private Date dataVencimentoCo;

    public JogadorEquipe() {}

    public JogadorEquipe(Integer id, Integer idEquipe, Integer idJogador, Date dataInicioContrato, Date dataVencimentoCo) {
        this.id = id;
        this.idEquipe = idEquipe;
        this.idJogador = idJogador;
        this.dataInicioContrato = dataInicioContrato;
        this.dataVencimentoCo = dataVencimentoCo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdEquipe() { return idEquipe; }
    public void setIdEquipe(Integer idEquipe) { this.idEquipe = idEquipe; }

    public Integer getIdJogador() { return idJogador; }
    public void setIdJogador(Integer idJogador) { this.idJogador = idJogador; }

    public Date getDataInicioContrato() { return dataInicioContrato; }
    public void setDataInicioContrato(Date dataInicioContrato) { this.dataInicioContrato = dataInicioContrato; }

    public Date getDataVencimentoCo() { return dataVencimentoCo; }
    public void setDataVencimentoCo(Date dataVencimentoCo) { this.dataVencimentoCo = dataVencimentoCo; }
}
