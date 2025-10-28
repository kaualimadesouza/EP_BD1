package org.example.goalytics.repository;

import org.example.goalytics.model.JogadorEquipe;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JogadorEquipeRepository {
    private final DataSource dataSource;

    public JogadorEquipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<JogadorEquipe> listarTodos() {
        String sql = "SELECT * FROM public.jogador_equipe;";
        List<JogadorEquipe> lista = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                JogadorEquipe je = new JogadorEquipe(
                        rs.getObject("id") != null ? rs.getInt("id") : null,
                        rs.getInt("id_equipe"),
                        rs.getInt("id_jogador"),
                        rs.getDate("data_inicio_contrato"),
                        rs.getDate("data_vencimento_co")
                );
                lista.add(je);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar jogador_equipe", e);
        }
        return lista;
    }

    public void inserir(JogadorEquipe jogadorEquipe) {
        String sql = "INSERT INTO public.jogador_equipe (id_equipe, id_jogador, data_inicio_contrato, data_vencimento_co) VALUES (?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, jogadorEquipe.getIdEquipe());
            statement.setInt(2, jogadorEquipe.getIdJogador());
            statement.setDate(3, jogadorEquipe.getDataInicioContrato() != null ? new java.sql.Date(jogadorEquipe.getDataInicioContrato().getTime()) : null);
            statement.setDate(4, jogadorEquipe.getDataVencimentoCo() != null ? new java.sql.Date(jogadorEquipe.getDataVencimentoCo().getTime()) : null);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir jogador_equipe", e);
        }
    }

    public void atualizar(JogadorEquipe jogadorEquipe) {
        String sql = "UPDATE public.jogador_equipe SET data_inicio_contrato = ?, data_vencimento_co = ? WHERE id_equipe = ? AND id_jogador = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, jogadorEquipe.getDataInicioContrato() != null ? new java.sql.Date(jogadorEquipe.getDataInicioContrato().getTime()) : null);
            statement.setDate(2, jogadorEquipe.getDataVencimentoCo() != null ? new java.sql.Date(jogadorEquipe.getDataVencimentoCo().getTime()) : null);
            statement.setInt(3, jogadorEquipe.getIdEquipe());
            statement.setInt(4, jogadorEquipe.getIdJogador());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar jogador_equipe", e);
        }
    }

    public void excluir(Integer idEquipe, Integer idJogador) {
        String sql = "DELETE FROM public.jogador_equipe WHERE id_equipe = ? AND id_jogador = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idEquipe);
            statement.setInt(2, idJogador);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir jogador_equipe", e);
        }
    }

    public JogadorEquipe obterPorId(Integer idEquipe, Integer idJogador) {
        String sql = "SELECT * FROM public.jogador_equipe WHERE id_equipe = ? AND id_jogador = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idEquipe);
            statement.setInt(2, idJogador);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new JogadorEquipe(
                        rs.getObject("id") != null ? rs.getInt("id") : null,
                        rs.getInt("id_equipe"),
                        rs.getInt("id_jogador"),
                        rs.getDate("data_inicio_contrato"),
                        rs.getDate("data_vencimento_co")
                );
            } else {
                throw new RuntimeException("JogadorEquipe não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter jogador_equipe", e);
        }
    }

    public JogadorEquipe obterPorId(Integer id) {
        String sql = "SELECT * FROM public.jogador_equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new JogadorEquipe(
                        rs.getObject("id") != null ? rs.getInt("id") : null,
                        rs.getInt("id_equipe"),
                        rs.getInt("id_jogador"),
                        rs.getDate("data_inicio_contrato"),
                        rs.getDate("data_vencimento_co")
                );
            } else {
                throw new RuntimeException("JogadorEquipe não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter jogador_equipe", e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'jogador_equipe';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela jogador_equipe", e);
        }
        return colunas;
    }
}
