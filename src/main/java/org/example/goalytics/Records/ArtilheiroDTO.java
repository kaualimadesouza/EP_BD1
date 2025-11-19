package org.example.goalytics.Records;

public record ArtilheiroDTO(
    Integer id,
    String nome,
    String posicao,
    Integer numJogos,
    Integer numGols,
    String fotoJogador
) {
}
