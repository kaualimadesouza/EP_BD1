create table partida_equipe_possui (
	id_partida INTEGER not null,
	id_equipe INTEGER not null,
	placar INTEGER not null,

	constraint fk_partida foreign key (id_partida) references partida(id),
	constraint fk_equipe foreign key (id_equipe) references equipe(id)
);