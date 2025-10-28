package org.example.goalytics.repository;

import org.example.goalytics.model.Estadio;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EstadioRepository {

    private final DataSource dataSource;

    public EstadioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Estadio> listarTodos() {
        String sql = "SELECT * FROM public.estadio;";
        List<Estadio> estadios = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Estadio estadio = new Estadio(
                        rs.getInt("id"),
                        rs.getString("nome_oficial"),
                        rs.getString("nome_apelido"),
                        rs.getInt("capacidade_atual"),
                        rs.getInt("capacidade_maxima"),
                        rs.getString("pais"),
                        rs.getString("endereco"),
                        rs.getDate("data_inauguracao"),
                        rs.getString("tipo_gramado")
                );
                estadios.add(estadio);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar estadios", e);
        }
        return estadios;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.estadio WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do estadio com id: " + id, e);
        }
    }

    public void excluirEstadio(Integer id) {
        String sql = "DELETE FROM public.estadio WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir estadio com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'estadio';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela estadio", e);
        }
        return colunas;
    }

    public void inserir(Estadio estadio) {
        String sql = "INSERT INTO public.estadio (nome_oficial, nome_apelido, capacidade_atual, capacidade_maxima, pais, endereco, data_inauguracao, tipo_gramado) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, estadio.getNomeOficial());
            statement.setString(2, estadio.getNomeApelido());
            statement.setInt(3, estadio.getCapacidadeAtual());
            statement.setInt(4, estadio.getCapacidadeMaxima());
            statement.setString(5, estadio.getPais());
            statement.setString(6, estadio.getEndereco());
            statement.setDate(7, estadio.getDataInauguracao() != null ? new java.sql.Date(estadio.getDataInauguracao().getTime()) : null);
            statement.setString(8, estadio.getTipoGramado());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir estadio", e);
        }
    }

    public void atualizar(Estadio estadio, Integer id) {
        String sql = "UPDATE public.estadio SET nome_oficial = ?, nome_apelido = ?, capacidade_atual = ?, capacidade_maxima = ?, pais = ?, endereco = ?, data_inauguracao = ?, tipo_gramado = ? WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, estadio.getNomeOficial());
            statement.setString(2, estadio.getNomeApelido());
            statement.setInt(3, estadio.getCapacidadeAtual());
            statement.setInt(4, estadio.getCapacidadeMaxima());
            statement.setString(5, estadio.getPais());
            statement.setString(6, estadio.getEndereco());
            statement.setDate(7, estadio.getDataInauguracao() != null ? new java.sql.Date(estadio.getDataInauguracao().getTime()) : null);
            statement.setString(8, estadio.getTipoGramado());
            statement.setInt(9, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar estadio com id: " + id, e);
        }
    }

    public Estadio obterPorId(Integer id) {
        String sql = "SELECT * FROM public.estadio WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Estadio(
                        rs.getInt("id"),
                        rs.getString("nome_oficial"),
                        rs.getString("nome_apelido"),
                        rs.getInt("capacidade_atual"),
                        rs.getInt("capacidade_maxima"),
                        rs.getString("pais"),
                        rs.getString("endereco"),
                        rs.getDate("data_inauguracao"),
                        rs.getString("tipo_gramado")
                );
            } else {
                throw new RuntimeException("Estádio com id " + id + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter estadio com id: " + id, e);
        }
    }
}
