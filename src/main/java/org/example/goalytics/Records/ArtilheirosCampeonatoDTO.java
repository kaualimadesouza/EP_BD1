package org.example.goalytics.Records;

import java.util.List;

public record ArtilheirosCampeonatoDTO(
    String nomeCampeonato,
    List<ArtilheiroDTO> artilheiros
) {
}
