package org.example.goalytics.Records;

import java.util.List;

public record CampeonatoJogosDTO(
        String iconCampeonato,
        String paisCampeonato,
        String nomeCampeonato,
        List<UltimosJogosCampeonatoDTO> ultimosJogos
) {
}
