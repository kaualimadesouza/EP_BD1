package org.example.goalytics.Records;

import java.util.List;

public record EstatisticasCampeonatoDTO(
    List<MediaGolsPorPartidaEmCampeonatoDTO> mediaGolsPorPartidaDTO,
    List<EstadiosNaoEstaNoCampeonatoDTO> estadiosNaoEstaNoCampeonatoDTO
) {
}
