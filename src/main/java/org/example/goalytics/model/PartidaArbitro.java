package org.example.goalytics.model;

public class PartidaArbitro {
    private Integer id;
    private Integer idPartida;
    private Integer idArbitro;
    private String funcaoArbitro;

    public PartidaArbitro() {}

    public PartidaArbitro(Integer id, Integer idPartida, Integer idArbitro, String funcaoArbitro) {
        this.id = id;
        this.idPartida = idPartida;
        this.idArbitro = idArbitro;
        this.funcaoArbitro = funcaoArbitro;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPartida() { return idPartida; }
    public void setIdPartida(Integer idPartida) { this.idPartida = idPartida; }

    public Integer getIdArbitro() { return idArbitro; }
    public void setIdArbitro(Integer idArbitro) { this.idArbitro = idArbitro; }

    public String getFuncaoArbitro() { return funcaoArbitro; }
    public void setFuncaoArbitro(String funcaoArbitro) { this.funcaoArbitro = funcaoArbitro; }
}

