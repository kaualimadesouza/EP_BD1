CREATE TABLE Campeonato (
    id SERIAL PRIMARY KEY,
    temporada INTEGER,
    tipo_campeonato VARCHAR(50),
    status VARCHAR(25),
    campeao VARCHAR(50),
    nome VARCHAR(100)
);