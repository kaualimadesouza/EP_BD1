package org.example.goalytics.Records;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record PartidaHistoricoPartidasDTO(
    Integer idJogo,
    LocalDate dataJogo,
    LocalTime horaJogo,
    EquipeJogoHistoricoDTO times
) {
}
