package org.example.goalytics.model;

public class PartidaEquipePossui {
    private Integer id;
    private Integer idPartida;
    private Integer idEquipe;
    private Integer placar;

    public PartidaEquipePossui() {}

    public PartidaEquipePossui(Integer id, Integer idPartida, Integer idEquipe, Integer placar) {
        this.id = id;
        this.idPartida = idPartida;
        this.idEquipe = idEquipe;
        this.placar = placar;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPartida() { return idPartida; }
    public void setIdPartida(Integer idPartida) { this.idPartida = idPartida; }

    public Integer getIdEquipe() { return idEquipe; }
    public void setIdEquipe(Integer idEquipe) { this.idEquipe = idEquipe; }

    public Integer getPlacar() { return placar; }
    public void setPlacar(Integer placar) { this.placar = placar; }
}
