package org.example.goalytics.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Partida {
    private Integer id;
    private Integer idEstadio;
    private Integer idCampeonato;
    private LocalDate data;
    private LocalTime horario;
    private String condicaoClimatica;
    private String status;

    public Partida() {}

    public Partida(Integer id, Integer idEstadio, Integer idCampeonato, LocalDate data, LocalTime horario, String condicaoClimatica, String status) {
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

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHorario() { return horario; }
    public void setHorario(Time horario) { this.horario = horario.toLocalTime(); }

    public String getCondicaoClimatica() { return condicaoClimatica; }
    public void setCondicaoClimatica(String condicaoClimatica) { this.condicaoClimatica = condicaoClimatica; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

