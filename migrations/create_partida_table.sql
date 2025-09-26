create table partida(
	id SERIAL primary key,
	id_estadio INTEGER not null,
	id_campeonato INTEGER not null,
	data DATE not null,
	horario time not null,
	condicao_climatica VARCHAR(255) not null,
	status CHAR(15) not null, -- (em andamento, concluido, programado)
	constraint fk_estadio foreign key (id_estadio) references estadio(id),
	constraint fk_campeonato foreign key (id_campeonato) references campeonato(id)
);