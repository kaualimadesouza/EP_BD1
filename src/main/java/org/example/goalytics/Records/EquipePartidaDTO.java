package org.example.goalytics.Records;

public record EquipePartidaDTO (
         Long id,
         String cidade,
         String nomePopular,
         String nomeOficial,
         String sigla,
         Integer placar
) {
}
