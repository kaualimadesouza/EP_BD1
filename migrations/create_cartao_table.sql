CREATE TABLE cartao (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL, -- Tanto jogador quanto tecnico podem receber cartão
    id_pessoa INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- Ex: 'Amarelo', 'Vermelho'
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometro TIMESTAMP NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id)

);