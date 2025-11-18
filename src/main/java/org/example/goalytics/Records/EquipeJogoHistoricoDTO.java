package org.example.goalytics.Records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EquipeJogoHistoricoDTO(
    @JsonProperty("timeCasa")
    EquipeHistoricoPartidasDTO timeCasa,

    @JsonProperty("timeVisitante")
    EquipeHistoricoPartidasDTO timeVisitante
) {
}
