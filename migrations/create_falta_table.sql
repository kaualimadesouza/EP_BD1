CREATE TABLE falta (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL,
    id_jogador_vitima INTEGER NOT NULL,
    id_jogador_infrator INTEGER NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    local VARCHAR(100) NOT NULL,
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometragem TIMESTAMP NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    CONSTRAINT fk_jogador_vitima FOREIGN KEY (id_jogador_vitima) REFERENCES jogador(id),
    CONSTRAINT fk_jogador_infrator FOREIGN KEY (id_jogador_infrator) REFERENCES jogador(id)
);