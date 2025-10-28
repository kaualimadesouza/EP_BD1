package org.example.goalytics.model;

import java.sql.Time;
import java.util.Date;

public class Partida {
    private Integer id;
    private Integer idEstadio;
    private Integer idCampeonato;
    private Date data;
    private Time horario;
    private String condicaoClimatica;
    private String status;

    public Partida() {}

    public Partida(Integer id, Integer idEstadio, Integer idCampeonato, Date data, Time horario, String condicaoClimatica, String status) {
        this.id = id;
        this.idEstadio = idEstadio;
        this.idCampeonato = idCampeonato;
        this.data = data;
        this.horario = horario;
        this.condicaoClimatica = condicaoClimatica;
        this.status = status;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdEstadio() { return idEstadio; }
    public void setIdEstadio(Integer idEstadio) { this.idEstadio = idEstadio; }

    public Integer getIdCampeonato() { return idCampeonato; }
    public void setIdCampeonato(Integer idCampeonato) { this.idCampeonato = idCampeonato; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Time getHorario() { return horario; }
    public void setHorario(Time horario) { this.horario = horario; }

    public String getCondicaoClimatica() { return condicaoClimatica; }
    public void setCondicaoClimatica(String condicaoClimatica) { this.condicaoClimatica = condicaoClimatica; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

