CREATE TABLE defesa (
    num_evento SERIAL not null,
    id_partida INTEGER not null,
    tipo VARCHAR(100) not null,
    id_defensor INTEGER not null,
    fase_da_partida VARCHAR(50) not null,
    cronometragem TIMESTAMP not null,
    id_evento_anterior INTEGER not null,

    PRIMARY KEY (num_evento, id_partida),

    constraint fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    constraint fk_defensor FOREIGN KEY (id_defensor) REFERENCES jogador(id)
);
