CREATE TABLE jogador (
    id SERIAL PRIMARY KEY,
    posicao CHAR(4) not null,
    pe_dominante VARCHAR(50) not null,
    altura NUMERIC(4,2) not null,
    preco NUMERIC(11,2) not null,
    numero_camisa INTEGER not null,
    nome_completo VARCHAR(255) not null,
    data_nascimento DATE not null,
    nacionalidade VARCHAR(255) not null
);