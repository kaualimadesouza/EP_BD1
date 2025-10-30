package org.example.goalytics.repository;

import org.example.goalytics.Records.EquipePartidaDTO;
import org.example.goalytics.model.Equipe;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class EquipeRepository {
    private static final Logger logger = LoggerFactory.getLogger(EquipeRepository.class);
    private final DataSource dataSource;

    public EquipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Equipe> listarTodos() {
        String sql = "SELECT * FROM public.equipe;";
        List<Equipe> equipes = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Equipe equipe = new Equipe(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("nome_popular"),
                        rs.getString("nome_oficial"),
                        rs.getDate("data_funcacao"),
                        rs.getString("sigla"),
                        rs.getString("nome_estadio")
                );
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar equipes", e);
        }
        return equipes;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência da equipe com id: " + id, e);
        }
    }

    public void excluirEquipe(Integer id) {
        String sql = "DELETE FROM public.equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir equipe com id: " + id, e);
        }
    }

    public void inserir(Equipe equipe) {
        String sql = "INSERT INTO public.equipe (estado, cidade, nome_popular, nome_oficial, data_funcacao, sigla, nome_estadio) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, equipe.getEstado());
            statement.setString(2, equipe.getCidade());
            statement.setString(3, equipe.getNomePopular());
            statement.setString(4, equipe.getNomeOficial());
            statement.setDate(5, equipe.getDataFuncacao() != null ? new java.sql.Date(equipe.getDataFuncacao().getTime()) : null);
            statement.setString(6, equipe.getSigla());
            statement.setString(7, equipe.getNomeEstadio());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir equipe", e);
        }
    }

    public void atualizar(Equipe equipe, Integer id) {
        String sql = "UPDATE public.equipe SET estado = ?, cidade = ?, nome_popular = ?, nome_oficial = ?, data_funcacao = ?, sigla = ?, nome_estadio = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, equipe.getEstado());
            statement.setString(2, equipe.getCidade());
            statement.setString(3, equipe.getNomePopular());
            statement.setString(4, equipe.getNomeOficial());
            statement.setDate(5, equipe.getDataFuncacao() != null ? new java.sql.Date(equipe.getDataFuncacao().getTime()) : null);
            statement.setString(6, equipe.getSigla());
            statement.setString(7, equipe.getNomeEstadio());
            statement.setInt(8, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar equipe com id: " + id, e);
        }
    }

    public Equipe obterPorId(Integer id) {
        String sql = "SELECT * FROM public.equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Equipe(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("nome_popular"),
                        rs.getString("nome_oficial"),
                        rs.getDate("data_funcacao"),
                        rs.getString("sigla"),
                        rs.getString("nome_estadio")
                );
            } else {
                throw new RuntimeException("Equipe com id " + id + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter equipe com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'equipe';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela equipe", e);
        }
        return colunas;
    }

    public List<EquipePartidaDTO> obterEquipesPorPartidaId(Integer id) {
        logger.info("Consultando equipes da partida com id {}", id);
        String sql = "select \n" +
                "\te.id,\n" +
                "\te.cidade,\n" +
                "\te.nome_popular,\n" +
                "\te.nome_oficial,\n" +
                "\te.sigla,\n" +
                "\tpep.placar\n" +
                "from equipe e\n" +
                "inner join public.partida_equipe_possui pep on e.id = pep.id_equipe\n" +
                "inner join public.partida p on p.id = pep.id_partida \n" +
                "where pep.id_partida = ?;";
        List<EquipePartidaDTO> equipes = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EquipePartidaDTO equipe = new EquipePartidaDTO(
                        rs.getLong("id"),
                        rs.getString("cidade"),
                        rs.getString("nome_popular"),
                        rs.getString("nome_oficial"),
                        rs.getString("sigla"),
                        rs.getInt("placar")
                );
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            logger.error("Erro ao listar equipes da partida com id {}: {}", id, e.getMessage());
            throw new RuntimeException("Erro ao listar equipes da partida com id: " + id, e);
        }
        return equipes;
    }
}
