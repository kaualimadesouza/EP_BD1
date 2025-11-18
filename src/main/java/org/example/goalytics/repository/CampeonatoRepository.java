package org.example.goalytics.repository;

import org.example.goalytics.model.Campeonato;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampeonatoRepository {
    private final DataSource dataSource;

    public CampeonatoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Campeonato> listarTodos() {
        String sql = "SELECT * FROM public.campeonato;";
        List<Campeonato> campeonatos = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Campeonato campeonato = new Campeonato(
                        rs.getInt("id"),
                        rs.getObject("temporada") != null ? rs.getInt("temporada") : null,
                        rs.getString("tipo_campeonato"),
                        rs.getString("status"),
                        rs.getString("campeao"),
                        rs.getString("nome"),
                        rs.getString("url_campeonato"),
                        rs.getString("regiao")
                );
                campeonatos.add(campeonato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar campeonatos", e);
        }
        return campeonatos;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do campeonato com id: " + id, e);
        }
    }

    public void excluirCampeonato(Integer id) {
        String sql = "DELETE FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir campeonato com id: " + id, e);
        }
    }

    public void inserir(Campeonato campeonato) {
        String sql = "INSERT INTO public.campeonato (temporada, tipo_campeonato, status, campeao, nome) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (campeonato.getTemporada() != null) {
                statement.setInt(1, campeonato.getTemporada());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, campeonato.getTipoCampeonato());
            statement.setString(3, campeonato.getStatus());
            statement.setString(4, campeonato.getCampeao());
            statement.setString(5, campeonato.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir campeonato", e);
        }
    }

    public void atualizar(Campeonato campeonato, Integer id) {
        String sql = "UPDATE public.campeonato SET temporada = ?, tipo_campeonato = ?, status = ?, campeao = ?, nome = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (campeonato.getTemporada() != null) {
                statement.setInt(1, campeonato.getTemporada());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, campeonato.getTipoCampeonato());
            statement.setString(3, campeonato.getStatus());
            statement.setString(4, campeonato.getCampeao());
            statement.setString(5, campeonato.getNome());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar campeonato com id: " + id, e);
        }
    }

    public Campeonato obterPorId(Integer id) {
        String sql = "SELECT * FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Campeonato(
                        rs.getInt("id"),
                        rs.getObject("temporada") != null ? rs.getInt("temporada") : null,
                        rs.getString("tipo_campeonato"),
                        rs.getString("status"),
                        rs.getString("campeao"),
                        rs.getString("nome"),
                        rs.getString("url_campeonato"),
                        rs.getString("regiao")
                );
            } else {
                throw new RuntimeException("Campeonato com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter campeonato com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'campeonato';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela campeonato", e);
        }
        return colunas;
    }
}

