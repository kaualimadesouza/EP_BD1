package org.example.goalytics.Records;

import java.util.ArrayList;
import java.util.List;

public record CampeonatoHistoricoPartidasDTO (
    Integer idCampeonato,
    String regiao,
    String iconCampeonato,
    String nomeCampeonato,
    List<PartidaHistoricoPartidasDTO> ultimosJogos
) {
}
