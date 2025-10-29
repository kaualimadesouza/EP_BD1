package org.example.goalytics.model;

import java.util.Date;

public class TecnicoEquipe {
    private Integer id;
    private Integer idEquipe;
    private Integer idTecnico;
    private Date dataInicioContrato;
    private Date dataVencimentoCo;

    public TecnicoEquipe() {}

    public TecnicoEquipe(Integer id, Integer idEquipe, Integer idTecnico, Date dataInicioContrato, Date dataVencimentoCo) {
        this.id = id;
        this.idEquipe = idEquipe;
        this.idTecnico = idTecnico;
        this.dataInicioContrato = dataInicioContrato;
        this.dataVencimentoCo = dataVencimentoCo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public Date getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public Date getDataVencimentoCo() {
        return dataVencimentoCo;
    }

    public void setDataVencimentoCo(Date dataVencimentoCo) {
        this.dataVencimentoCo = dataVencimentoCo;
    }
}
