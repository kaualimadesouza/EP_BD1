-- Inserts para a tabela arbitro
INSERT INTO arbitro (federacao, nome_completo, data_nascimento) VALUES
('CBF', 'Carlos Alberto Pereira', '1975-03-15'),
('FIFA', 'Maria Fernanda Silva', '1980-07-22'),
('CBF', 'João Pedro Santos', '1978-11-01');

-- Inserts para a tabela campeonato
INSERT INTO campeonato (temporada, tipo_campeonato, status, campeao, nome) VALUES
(2024, 'Nacional', 'Concluído', 'Flamengo', 'Campeonato Brasileiro Série A'),
(2024, 'Estadual', 'Em Andamento', NULL, 'Campeonato Carioca'),
(2025, 'Internacional', 'Programado', NULL, 'Copa Libertadores');

-- Inserts para a tabela estadio
INSERT INTO estadio (nome_oficial, nome_apelido, capacidade_atual, capacidade_maxima, pais, endereco, data_inauguracao, tipo_gramado) VALUES
('Estádio Jornalista Mário Filho', 'Maracanã', 78838, 78838, 'Brasil', 'Av. Presidente Castelo Branco, Portão 3 - Maracanã, Rio de Janeiro - RJ', '1950-06-16', 'Grama Natural'),
('Arena Corinthians', 'Neo Química Arena', 49205, 49205, 'Brasil', 'Av. Miguel Ignácio Curi, 111 - Artur Alvim, São Paulo - SP', '2014-05-10', 'Grama Natural'),
('Estádio Mineirão', 'Mineirão', 61927, 61927, 'Brasil', 'Av. Antônio Abrahão Caram, 1001 - São José, Belo Horizonte - MG', '1965-09-05', 'Grama Natural');

-- Inserts para a tabela equipe
INSERT INTO equipe (estado, cidade, nome_popular, nome_oficial, data_funcacao, sigla, nome_estadio) VALUES
('Rio de Janeiro', 'Rio de Janeiro', 'Flamengo', 'Clube de Regatas do Flamengo', '1895-11-15', 'FLA', 'Maracanã'),
('São Paulo', 'São Paulo', 'Corinthians', 'Sport Club Corinthians Paulista', '1910-09-01', 'COR', 'Neo Química Arena'),
('Minas Gerais', 'Belo Horizonte', 'Atlético-MG', 'Clube Atlético Mineiro', '1908-03-25', 'CAM', 'Mineirão');

-- Inserts para a tabela jogador
INSERT INTO jogador (posicao, pe_dominante, altura, preco, numero_camisa, nome_completo, data_nascimento, nacionalidade) VALUES
('ATA', 'Direito', 1.75, 15000000.00, 9, 'Gabriel Barbosa', '1996-08-30', 'Brasileiro'),
('MEI', 'Esquerdo', 1.80, 10000000.00, 10, 'Renato Augusto', '1988-02-08', 'Brasileiro'),
('ZAG', 'Direito', 1.88, 8000000.00, 4, 'Gil', '1987-06-17', 'Brasileiro'),
('GOL', 'Direito', 1.92, 5000000.00, 1, 'Cássio Ramos', '1987-09-29', 'Brasileiro'),
('LAT', 'Esquerdo', 1.70, 7000000.00, 6, 'Filipe Luís', '1985-08-09', 'Brasileiro');

-- Inserts para a tabela tecnico
INSERT INTO tecnico (formacao_fav, nome_completo, data_nascimento, nacionalidade) VALUES
('4-3-3', 'Tite', '1961-05-25', 'Brasileiro'),
('4-2-3-1', 'Vítor Pereira', '1968-07-26', 'Português'),
('3-5-2', 'Jorge Sampaoli', '1960-03-13', 'Argentino');

-- Inserts para a tabela campeonato_equipe
INSERT INTO campeonato_equipe (id_campeonato, id_equipe, colocacao_ou_fase) VALUES
(1, 1, 'Campeão'),
(1, 2, 'Vice-campeão'),
(2, 1, 'Finalista'),
(2, 3, 'Semifinalista');

-- Inserts para a tabela jogador_equipe
INSERT INTO jogador_equipe (id_equipe, id_jogador, data_inicio_contrato, data_vencimento_co) VALUES
(1, 1, '2019-01-01', '2025-12-31'),
(2, 2, '2021-08-01', '2024-12-31'),
(2, 3, '2019-01-01', '2024-12-31'),
(2, 4, '2012-01-01', '2025-12-31'),
(1, 5, '2019-07-23', '2024-12-31');

-- Inserts para a tabela tecnico_equipe
INSERT INTO tecnico_equipe (id_equipe, id_tecnico, data_inicio_contrato, data_vencimento_co) VALUES
(1, 1, '2023-01-01', '2024-12-31'),
(2, 2, '2022-01-01', '2023-12-31'),
(3, 3, '2023-03-01', '2024-12-31');

-- Inserts para a tabela partida
INSERT INTO partida (id_estadio, id_campeonato, data, horario, condicao_climatica, status) VALUES
(1, 1, '2024-10-26', '16:00:00', 'Ensolarado', 'Concluído'),
(2, 1, '2024-10-27', '18:30:00', 'Nublado', 'Concluído'),
(1, 2, '2024-11-05', '21:00:00', 'Chuvoso', 'Programado');

-- Inserts para a tabela partida_arbitro
INSERT INTO partida_arbitro (id_partida, id_arbitro, funcao_arbitro) VALUES
(1, 1, 'Principal'),
(1, 2, 'VAR'),
(2, 3, 'Principal'),
(3, 1, 'Principal');

-- Inserts para a tabela partida_equipe_possui
INSERT INTO partida_equipe_possui (id_partida, id_equipe, placar) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 2, 0),
(2, 3, 0);

-- Inserts para a tabela cartao
INSERT INTO cartao (num_evento, id_partida, id_pessoa, tipo, fase_da_partida, cronometro, id_evento_anterior) VALUES
(1, 1, 1, 'Amarelo', 'Primeiro Tempo', '2024-10-26 16:20:00', 0),
(2, 1, 2, 'Amarelo', 'Segundo Tempo', '2024-10-26 17:10:00', 1),
(3, 2, 3, 'Vermelho', 'Primeiro Tempo', '2024-10-27 18:45:00', 0);

-- Inserts para a tabela defesa
INSERT INTO defesa (num_evento, id_partida, tipo, id_defensor, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 'Defesa com as mãos', 4, 'Primeiro Tempo', '2024-10-26 16:15:00', 0),
(2, 1, 'Defesa com os pés', 4, 'Segundo Tempo', '2024-10-26 17:05:00', 1);

-- Inserts para a tabela drible
INSERT INTO drible (num_evento, id_partida, id_jogador_executor, tipo, sucesso, duracao, x, y, z, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 1, 'Caneta', TRUE, '00:00:03', 0.750, 0.250, 0.000, 'Primeiro Tempo', '2024-10-26 16:10:00', 0),
(2, 1, 2, 'Elástico', FALSE, '00:00:02', 0.300, 0.600, 0.000, 'Segundo Tempo', '2024-10-26 17:25:00', 1);

-- Inserts para a tabela escanteio
INSERT INTO escanteio (num_evento, id_partida, lado, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 'Direito', 'Primeiro Tempo', '2024-10-26 16:05:00', 0),
(2, 1, 'Esquerdo', 'Segundo Tempo', '2024-10-26 17:15:00', 1);

-- Inserts para a tabela falta
INSERT INTO falta (num_evento, id_partida, id_jogador_vitima, id_jogador_infrator, tipo, local, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 1, 3, 'Carrinho', 'Meio Campo', 'Primeiro Tempo', '2024-10-26 16:18:00', 0),
(2, 1, 2, 5, 'Mão na bola', 'Área', 'Segundo Tempo', '2024-10-26 17:30:00', 1);

-- Inserts para a tabela finalizacao
INSERT INTO finalizacao (num_evento, id_partida, id_jogador, sucesso, parte_do_corpo, duracao, posicao_x_origem, posicao_y_origem, posicao_z_origem, posicao_x_destino, posicao_y_destino, posicao_z_destino, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 1, TRUE, 'Pé Direito', '00:00:02', 0.800, 0.400, 0.000, 0.990, 0.500, 0.000, 'Primeiro Tempo', '16:12:00', 0),
(2, 1, 2, FALSE, 'Cabeça', '00:00:01', 0.700, 0.600, 0.000, 0.900, 0.700, 0.000, 'Segundo Tempo', '17:20:00', 1);

-- Inserts para a tabela impedimento
INSERT INTO impedimento (num_evento, id_partida, id_jogador, x, y, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 1, 0.700, 0.500, 'Primeiro Tempo', '2024-10-26 16:08:00', 0),
(2, 1, 2, 0.650, 0.450, 'Segundo Tempo', '2024-10-26 17:02:00', 1);

-- Inserts para a tabela passe
INSERT INTO passe (num_evento, id_partida, id_jogador_origem, id_jogador_destino, sucesso, duracao, posicao_x_origem, posicao_y_origem, posicao_z_origem, posicao_x_destino, posicao_y_destino, posicao_z_destino, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 2, 1, TRUE, '00:00:01', 0.300, 0.500, 0.000, 0.600, 0.500, 0.000, 'Primeiro Tempo', '2024-10-26 16:03:00', 0),
(2, 1, 1, 5, TRUE, '00:00:02', 0.600, 0.500, 0.000, 0.800, 0.200, 0.000, 'Segundo Tempo', '2024-10-26 17:00:00', 1);

-- Inserts para a tabela substituicao
INSERT INTO substituicao (num_evento, id_partida, id_pessoa_sai, id_pessoa_entra, fase_da_partida, cronometragem, id_evento_anterior) VALUES
(1, 1, 2, 5, 'Segundo Tempo', '2024-10-26 17:00:00', 0),
(2, 1, 3, 1, 'Segundo Tempo', '2024-10-26 17:35:00', 1);
