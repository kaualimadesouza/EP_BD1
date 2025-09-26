create table tecnico_equipe(
	id_equipe INTEGER not null,
	id_tecnico INTEGER not null,
	data_inicio_contrato DATE not null,
	data_vencimento_co DATE not null,


	CONSTRAINT fk_equipe FOREIGN KEY (id_equipe) REFERENCES equipe(id),
	CONSTRAINT fk_tecnico FOREIGN KEY (id_tecnico) REFERENCES tecnico(id)
);
