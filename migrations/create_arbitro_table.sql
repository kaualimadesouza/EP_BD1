create table arbitro (
	id SERIAL primary key,
	federacao VARCHAR(255) not null,
	nome_completo VARCHAR(255) not null,
	data_nascimento date not null
);
