CREATE TABLE tecnico (
    id SERIAL PRIMARY KEY,
    formacao_fav CHAR(5) not null,
    nome_completo VARCHAR(255) not null,
    data_nascimento DATE not null,
    nacionalidade VARCHAR(255) not null
);
