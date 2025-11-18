package org.example.goalytics.Records;

import java.sql.Date;
import java.sql.Time;

public record PartidaDetalhesDTO(
        Long id,
        Date data,
        Time horario,
        String condicaoClimatica,
        String status,
        Integer temporadaCampeonato, // <-- Mudou
        String campeao,
        String nomeCampeonato,     // <-- Mudou
        String nomeOficialEstadio, // <-- Mudou
        String nomeApelidoEstadio, // <-- Mudou
        String pais
) {}