CREATE TABLE passe (
    num_evento SERIAL NOT NULL,
    id_partida INTEGER NOT NULL,
    id_jogador_origem INTEGER NOT NULL,
    id_jogador_destino INTEGER NOT NULL,
    sucesso BOOLEAN NOT NULL,
    duracao TIMESTAMP NOT NULL,
    posicao_x_origem NUMERIC(3,3) NOT NULL,
    posicao_y_origem NUMERIC(3,3) NOT NULL,
    posicao_z_origem NUMERIC(3,3) NOT NULL,
    posicao_x_destino NUMERIC(3,3) NOT NULL,
    posicao_y_destino NUMERIC(3,3) NOT NULL,
    posicao_z_destino NUMERIC(3,3) NOT NULL,
    fase_da_partida VARCHAR(50) NOT NULL,
    cronometragem TIMESTAMP NOT NULL,
    id_evento_anterior INTEGER NOT NULL,

    PRIMARY KEY (num_evento, id_partida),

    CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
    CONSTRAINT fk_jogador_origem FOREIGN KEY (id_jogador_origem) REFERENCES jogador(id),
    CONSTRAINT fk_jogador_destino FOREIGN KEY (id_jogador_destino) REFERENCES jogador(id)
);