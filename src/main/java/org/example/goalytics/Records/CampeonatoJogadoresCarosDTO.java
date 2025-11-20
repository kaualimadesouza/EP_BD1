package org.example.goalytics.Records;

import java.util.List;

public record CampeonatoJogadoresCarosDTO(
    String nomeCampeonato,
    List<JogadoresMaisCarosPorCompeticao> jogadoresMaisCaros
) {
}
