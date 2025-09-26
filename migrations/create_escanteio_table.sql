CREATE TABLE escanteio (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL,
    lado VARCHAR(50) NOT NULL,
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometragem TIMESTAMP NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id)
);