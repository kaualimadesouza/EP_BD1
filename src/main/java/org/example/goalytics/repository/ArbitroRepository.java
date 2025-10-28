package org.example.goalytics.repository;

import org.example.goalytics.model.Arbitro;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArbitroRepository {
    private final DataSource dataSource;

    public ArbitroRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Arbitro> listarTodos() {
        String sql = "SELECT * FROM public.arbitro;";
        List<Arbitro> arbitros = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Arbitro arbitro = new Arbitro(
                        rs.getInt("id"),
                        rs.getString("federacao"),
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento")
                );
                arbitros.add(arbitro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar árbitros", e);
        }
        return arbitros;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.arbitro WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do árbitro com id: " + id, e);
        }
    }

    public void excluirArbitro(Integer id) {
        String sql = "DELETE FROM public.arbitro WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir árbitro com id: " + id, e);
        }
    }

    public void inserir(Arbitro arbitro) {
        String sql = "INSERT INTO public.arbitro (federacao, nome_completo, data_nascimento) VALUES (?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, arbitro.getFederacao());
            statement.setString(2, arbitro.getNomeCompleto());
            statement.setDate(3, arbitro.getDataNascimento() != null ? new java.sql.Date(arbitro.getDataNascimento().getTime()) : null);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir árbitro", e);
        }
    }

    public void atualizar(Arbitro arbitro, Integer id) {
        String sql = "UPDATE public.arbitro SET federacao = ?, nome_completo = ?, data_nascimento = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, arbitro.getFederacao());
            statement.setString(2, arbitro.getNomeCompleto());
            statement.setDate(3, arbitro.getDataNascimento() != null ? new java.sql.Date(arbitro.getDataNascimento().getTime()) : null);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar árbitro com id: " + id, e);
        }
    }

    public Arbitro obterPorId(Integer id) {
        String sql = "SELECT * FROM public.arbitro WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Arbitro(
                        rs.getInt("id"),
                        rs.getString("federacao"),
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento")
                );
            } else {
                throw new RuntimeException("Árbitro com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter árbitro com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'arbitro';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela arbitro", e);
        }
        return colunas;
    }
}
