CREATE TABLE substituicao (
    num_evento SERIAL,
    id_partida INTEGER not null,
    id_pessoa_sai INTEGER not null,
    id_pessoa_entra INTEGER not null,
    fase_da_partida VARCHAR(50) not null,
    cronometragem TIMESTAMP not null,
    id_evento_anterior INTEGER not null,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    CONSTRAINT fk_pessoa_sai FOREIGN KEY (id_pessoa_sai) REFERENCES jogador(id),
    CONSTRAINT fk_pessoa_entra FOREIGN KEY (id_pessoa_entra) REFERENCES jogador(id)
);