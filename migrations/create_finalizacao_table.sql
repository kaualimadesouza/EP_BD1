CREATE TABLE finalizacao (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL,
    id_jogador INTEGER NOT NULL,
    sucesso BOOLEAN NOT NULL,
    parte_do_corpo VARCHAR(100) NOT NULL,
    duracao TIMESTAMP NOT NULL,
    posicao_x_origem NUMERIC(3,3) NOT NULL,
    posicao_y_origem NUMERIC(3,3) NOT NULL,
    posicao_z_origem NUMERIC(3,3) NOT NULL,
    posicao_x_destino NUMERIC(3,3) NOT NULL,
    posicao_y_destino NUMERIC(3,3) NOT NULL,
    posicao_z_destino NUMERIC(3,3) NOT NULL,
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometragem TIME NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    CONSTRAINT fk_jogador FOREIGN KEY (id_jogador) REFERENCES jogador(id)
);