create table equipe(
	id SERIAL primary key,
	estado VARCHAR(255) not null,
	cidade VARCHAR(255) not null,
	nome_popular VARCHAR(50),
	nome_oficial VARCHAR(255) not null,
	data_funcacao DATE not null,
	sigla CHAR(3),
	nome_estadio VARCHAR(255) not null
);
