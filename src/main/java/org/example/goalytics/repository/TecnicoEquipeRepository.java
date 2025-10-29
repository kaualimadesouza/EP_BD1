package org.example.goalytics.repository;

import org.example.goalytics.model.TecnicoEquipe;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TecnicoEquipeRepository {

    private final DataSource dataSource;

    public TecnicoEquipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TecnicoEquipe> listarTodos() {
        String sql = "SELECT id, id_equipe, id_tecnico, data_inicio_contrato, data_vencimento_co FROM public.tecnico_equipe;";
        List<TecnicoEquipe> tecnicoEquipes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                TecnicoEquipe tecnicoEquipe = new TecnicoEquipe(
                        rs.getInt("id"),
                        rs.getInt("id_equipe"),
                        rs.getInt("id_tecnico"),
                        rs.getDate("data_inicio_contrato"),
                        rs.getDate("data_vencimento_co")
                );
                tecnicoEquipes.add(tecnicoEquipe);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tecnico_equipes", e);
        }
        return tecnicoEquipes;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.tecnico_equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do tecnico_equipe com id: " + id, e);
        }
    }

    public void excluirTecnicoEquipe(Integer id) {
        String sql = "DELETE FROM public.tecnico_equipe WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tecnico_equipe com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'tecnico_equipe';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela tecnico_equipe", e);
        }
        return colunas;
    }

    public void inserir(TecnicoEquipe tecnicoEquipe) {
        String sql = "INSERT INTO public.tecnico_equipe (id_equipe, id_tecnico, data_inicio_contrato, data_vencimento_co) VALUES (?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, tecnicoEquipe.getIdEquipe());
            statement.setInt(2, tecnicoEquipe.getIdTecnico());
            statement.setDate(3, tecnicoEquipe.getDataInicioContrato() != null ? new java.sql.Date(tecnicoEquipe.getDataInicioContrato().getTime()) : null);
            statement.setDate(4, tecnicoEquipe.getDataVencimentoCo() != null ? new java.sql.Date(tecnicoEquipe.getDataVencimentoCo().getTime()) : null);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tecnicoEquipe.setId(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tecnico_equipe", e);
        }
    }

    public void atualizar(TecnicoEquipe tecnicoEquipe, Integer id) {
        String sql = "UPDATE public.tecnico_equipe SET id_equipe = ?, id_tecnico = ?, data_inicio_contrato = ?, data_vencimento_co = ? WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, tecnicoEquipe.getIdEquipe());
            statement.setInt(2, tecnicoEquipe.getIdTecnico());
            statement.setDate(3, tecnicoEquipe.getDataInicioContrato() != null ? new java.sql.Date(tecnicoEquipe.getDataInicioContrato().getTime()) : null);
            statement.setDate(4, tecnicoEquipe.getDataVencimentoCo() != null ? new java.sql.Date(tecnicoEquipe.getDataVencimentoCo().getTime()) : null);
            statement.setInt(5, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tecnico_equipe com id: " + id, e);
        }
    }

    public TecnicoEquipe obterPorId(Integer id) {
        String sql = "SELECT id, id_equipe, id_tecnico, data_inicio_contrato, data_vencimento_co FROM public.tecnico_equipe WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new TecnicoEquipe(
                        rs.getInt("id"),
                        rs.getInt("id_equipe"),
                        rs.getInt("id_tecnico"),
                        rs.getDate("data_inicio_contrato"),
                        rs.getDate("data_vencimento_co")
                );
            } else {
                throw new RuntimeException("TecnicoEquipe com id " + id + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter tecnico_equipe com id: " + id, e);
        }
    }
}
