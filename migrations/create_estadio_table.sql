create table estadio (
	id SERIAL primary key,
	nome_oficial VARCHAR(255) not null,
	nome_apelido VARCHAR(50),
	capacidade_atual INTEGER not null,
	capacidade_maxima INTEGER not null,
	pais VARCHAR(50) not null,
	endereco VARCHAR(255) not null,
	data_inauguracao DATE not null,
	tipo_gramado VARCHAR(100)
);
