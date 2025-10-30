CREATE TABLE campeonato_equipe (
    id_campeonato INTEGER not null,
    id_equipe INTEGER not null,
    colocacao_ou_fase VARCHAR(100)

    CONSTRAINT fk_campeonato FOREIGN KEY (id_campeonato) REFERENCES campeonato(id),
    CONSTRAINT fk_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);

