create table partida_arbitro(
	id_partida INTEGER not null,
	id_arbitro INTEGER not null,
	funcao_arbitro VARCHAR(50),

	CONSTRAINT fk_partida FOREIGN KEY (id_partida) REFERENCES partida(id),
	CONSTRAINT fk_arbitro FOREIGN KEY (id_arbitro) REFERENCES arbitro(id)
);