package org.example.goalytics.repository;

import org.example.goalytics.model.Estadio;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}

