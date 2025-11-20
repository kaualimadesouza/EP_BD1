package org.example.goalytics.repository;

import org.example.goalytics.Records.ArtilheiroDTO;
import org.example.goalytics.Records.JogadoresMaisCarosPorCompeticao;
import org.example.goalytics.model.Jogador;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Repository
public class JogadorRepository {
    private final DataSource dataSource;

    public JogadorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Jogador> listarTodos() {
        String sql = "SELECT * FROM public.jogador;";
        List<Jogador> jogadores = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Jogador jogador = new Jogador(
                        rs.getInt("id"),
                        rs.getString("posicao"),
                        rs.getString("pe_dominante"),
                        rs.getBigDecimal("altura"),
                        rs.getBigDecimal("preco"),
                        rs.getObject("numero_camisa") != null ? rs.getInt("numero_camisa") : null,
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento"),
                        rs.getString("nacionalidade"),
                        rs.getString("nome_popular")
                );
                jogadores.add(jogador);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar jogadores", e);
        }
        return jogadores;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.jogador WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do jogador com id: " + id, e);
        }
    }

    public void excluirJogador(Integer id) {
        String sql = "DELETE FROM public.jogador WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir jogador com id: " + id, e);
        }
    }

    public void inserir(Jogador jogador) {
        String sql = "INSERT INTO public.jogador (posicao, pe_dominante, altura, preco, numero_camisa, nome_completo, data_nascimento, nacionalidade, nome_popular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, jogador.getPosicao());
            statement.setString(2, jogador.getPeDominante());
            statement.setBigDecimal(3, jogador.getAltura());
            statement.setBigDecimal(4, jogador.getPreco());
            if (jogador.getNumeroCamisa() != null) {
                statement.setInt(5, jogador.getNumeroCamisa());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, jogador.getNomeCompleto());
            statement.setDate(7, jogador.getDataNascimento() != null ? new java.sql.Date(jogador.getDataNascimento().getTime()) : null);
            statement.setString(8, jogador.getNacionalidade());
            statement.setString(9, jogador.getNomePopular());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir jogador", e);
        }
    }

    public void atualizar(Jogador jogador, Integer id) {
        String sql = "UPDATE public.jogador SET posicao = ?, pe_dominante = ?, altura = ?, preco = ?, numero_camisa = ?, nome_completo = ?, data_nascimento = ?, nacionalidade = ?, nome_popular = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, jogador.getPosicao());
            statement.setString(2, jogador.getPeDominante());
            statement.setBigDecimal(3, jogador.getAltura());
            statement.setBigDecimal(4, jogador.getPreco());
            if (jogador.getNumeroCamisa() != null) {
                statement.setInt(5, jogador.getNumeroCamisa());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setString(6, jogador.getNomeCompleto());
            statement.setDate(7, jogador.getDataNascimento() != null ? new java.sql.Date(jogador.getDataNascimento().getTime()) : null);
            statement.setString(8, jogador.getNacionalidade());
            statement.setString(9, jogador.getNomePopular());
            statement.setInt(10, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar jogador com id: " + id, e);
        }
    }

    public Jogador obterPorId(Integer id) {
        String sql = "SELECT * FROM public.jogador WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Jogador(
                        rs.getInt("id"),
                        rs.getString("posicao"),
                        rs.getString("pe_dominante"),
                        rs.getBigDecimal("altura"),
                        rs.getBigDecimal("preco"),
                        rs.getObject("numero_camisa") != null ? rs.getInt("numero_camisa") : null,
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento"),
                        rs.getString("nacionalidade"),
                        rs.getString("nome_popular")
                );
            } else {
                throw new RuntimeException("Jogador com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter jogador com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'jogador';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela jogador", e);
        }
        return colunas;
    }

    public List<ArtilheiroDTO> obterArtilheirosPorCampeonato(Integer id) {
        String query = "SELECT\n" +
                "    j.id,\n" +
                "    j.nome_completo AS nome_jogador,\n" +
                "    j.posicao,\n" +
                "    (\n" +
                "        SELECT COUNT(f.id_partida)\n" +
                "        FROM finalizacao f\n" +
                "        JOIN partida p ON f.id_partida = p.id \n" +
                "        WHERE f.id_jogador = j.id\n" +
                "          AND f.sucesso = TRUE \n" +
                "          AND p.id_campeonato = ?\n" +
                "    ) AS num_gols,\n" +
                "    (\n" +
                "    \tselect \n" +
                "\t\t\tcount(p.id)\n" +
                "\t\tfrom partida p\n" +
                "\t\tinner join partida_equipe_possui pep on p.id = pep.id_partida\n" +
                "\t\tinner join equipe e on pep.id_equipe = e.id\n" +
                "\t\tinner join jogador_equipe je on e.id = je.id_equipe \n" +
                "\t\tinner join jogador j2 on j2.id = je.id_jogador\n" +
                "\t\twhere j2.id = j.id\n" +
                "    ) as num_partidas,\n" +
                "    j.url_foto_jogador\n" +
                "FROM jogador j\n" +
                "JOIN jogador_equipe je ON j.id = je.id_jogador\n" +
                "CROSS JOIN campeonato c\n" +
                "WHERE c.id = ?\n" +
                "  AND (\n" +
                "        SELECT COUNT(f.id_partida)\n" +
                "        FROM finalizacao f\n" +
                "        JOIN partida p ON f.id_partida = p.id \n" +
                "        WHERE f.id_jogador = j.id\n" +
                "          AND f.sucesso = TRUE \n" +
                "          AND p.id_campeonato = ?\n" +
                "      ) > 0\n" +
                "ORDER BY num_gols DESC LIMIT 10;";

        List<ArtilheiroDTO> artilheiros = getArtilheiroDTOS(id, query);

        return artilheiros;
    }

    private List<ArtilheiroDTO> getArtilheiroDTOS(Integer id, String query) {
        List<ArtilheiroDTO> artilheiros = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.setInt(3, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ArtilheiroDTO artilheiro = new ArtilheiroDTO(
                        rs.getInt("id"),
                        rs.getString("nome_jogador"),
                        rs.getString("posicao"),
                        rs.getInt("num_partidas"),
                        rs.getInt("num_gols"),
                        rs.getString("url_foto_jogador")
                );
                artilheiros.add(artilheiro);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter artilheiros para o campeonato com id: " + id, e);
        }
        return artilheiros;
    }

    public List<JogadoresMaisCarosPorCompeticao> obterJogadoresMaisCarosPorCampeonato(Integer id) {
        String query = "SELECT\n" +
                "    MAX(j.preco) as max_preco,\n" +
                "    j.nome_completo,\n" +
                "    j.url_foto_jogador \n" +
                "FROM\n" +
                "    jogador j\n" +
                "JOIN\n" +
                "    jogador_equipe je ON j.id = je.id_jogador\n" +
                "JOIN\n" +
                "    campeonato_equipe ce ON je.id_equipe = ce.id_equipe\n" +
                "WHERE\n" +
                "    ce.id_campeonato = ?\n" +
                "group by j.nome_completo, j.url_foto_jogador\n" +
                "order by max_preco desc\n" +
                "limit 10;";

        List<JogadoresMaisCarosPorCompeticao> jogadoresMaisCaros = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                JogadoresMaisCarosPorCompeticao jogador = new JogadoresMaisCarosPorCompeticao(
                        rs.getString("nome_completo"),
                        rs.getDouble("max_preco"),
                        rs.getString("url_foto_jogador")
                );
                jogadoresMaisCaros.add(jogador);
            }
            return jogadoresMaisCaros;
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter jogadores mais caros para o campeonato com id: " + id, e);
        }
    }
}
