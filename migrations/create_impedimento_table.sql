CREATE TABLE impedimento (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL,
    id_jogador INTEGER NOT NULL,
    x NUMERIC(3,3) NOT NULL,
    y NUMERIC(3,3) NOT NULL,
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometragem TIMESTAMP NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES jogador(id)
);