package org.example.goalytics.repository;

import org.example.goalytics.model.Tecnico;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TecnicoRepository {
    private final DataSource dataSource;

    public TecnicoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Tecnico> listarTodos() {
        String sql = "SELECT * FROM public.tecnico;";
        List<Tecnico> tecnicos = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tecnico tecnico = new Tecnico(
                        rs.getInt("id"),
                        rs.getString("formacao_fav"),
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento"),
                        rs.getString("nacionalidade")
                );
                tecnicos.add(tecnico);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tecnicos", e);
        }
        return tecnicos;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.tecnico WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do tecnico com id: " + id, e);
        }
    }

    public void excluirTecnico(Integer id) {
        String sql = "DELETE FROM public.tecnico WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tecnico com id: " + id, e);
        }
    }

    public void inserir(Tecnico tecnico) {
        String sql = "INSERT INTO public.tecnico (formacao_fav, nome_completo, data_nascimento, nacionalidade) VALUES (?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tecnico.getFormacaoFav());
            statement.setString(2, tecnico.getNomeCompleto());
            statement.setDate(3, tecnico.getDataNascimento() != null ? new java.sql.Date(tecnico.getDataNascimento().getTime()) : null);
            statement.setString(4, tecnico.getNacionalidade());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tecnico", e);
        }
    }

    public void atualizar(Tecnico tecnico, Integer id) {
        String sql = "UPDATE public.tecnico SET formacao_fav = ?, nome_completo = ?, data_nascimento = ?, nacionalidade = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tecnico.getFormacaoFav());
            statement.setString(2, tecnico.getNomeCompleto());
            statement.setDate(3, tecnico.getDataNascimento() != null ? new java.sql.Date(tecnico.getDataNascimento().getTime()) : null);
            statement.setString(4, tecnico.getNacionalidade());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tecnico com id: " + id, e);
        }
    }

    public Tecnico obterPorId(Integer id) {
        String sql = "SELECT * FROM public.tecnico WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Tecnico(
                        rs.getInt("id"),
                        rs.getString("formacao_fav"),
                        rs.getString("nome_completo"),
                        rs.getDate("data_nascimento"),
                        rs.getString("nacionalidade")
                );
            } else {
                throw new RuntimeException("Tecnico com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter tecnico com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'tecnico';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela tecnico", e);
        }
        return colunas;
    }
}

