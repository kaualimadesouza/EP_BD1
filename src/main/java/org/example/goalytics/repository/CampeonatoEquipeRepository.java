package org.example.goalytics.repository;

import org.example.goalytics.model.CampeonatoEquipe;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampeonatoEquipeRepository {

    private final DataSource dataSource;

    public CampeonatoEquipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CampeonatoEquipe> listarTodos() {
        String sql = "SELECT id, id_campeonato, id_equipe, colocacao_ou_fase FROM public.campeonato_equipe;";
        List<CampeonatoEquipe> campeonatoEquipes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                CampeonatoEquipe campeonatoEquipe = new CampeonatoEquipe(
                        rs.getInt("id"),
                        rs.getInt("id_campeonato"),
                        rs.getInt("id_equipe"),
                        rs.getString("colocacao_ou_fase")
                );
                campeonatoEquipes.add(campeonatoEquipe);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar campeonato_equipes", e);
        }
        return campeonatoEquipes;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.campeonato_equipe WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do campeonato_equipe com id: " + id, e);
        }
    }

    public void excluirCampeonatoEquipe(Integer id) {
        String sql = "DELETE FROM public.campeonato_equipe WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir campeonato_equipe com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'campeonato_equipe';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela campeonato_equipe", e);
        }
        return colunas;
    }

    public void inserir(CampeonatoEquipe campeonatoEquipe) {
        String sql = "INSERT INTO public.campeonato_equipe (id_campeonato, id_equipe, colocacao_ou_fase) VALUES (?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, campeonatoEquipe.getIdCampeonato());
            statement.setInt(2, campeonatoEquipe.getIdEquipe());
            statement.setString(3, campeonatoEquipe.getColocacaoOuFase());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    campeonatoEquipe.setId(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir campeonato_equipe", e);
        }
    }

    public void atualizar(CampeonatoEquipe campeonatoEquipe, Integer id) {
        String sql = "UPDATE public.campeonato_equipe SET id_campeonato = ?, id_equipe = ?, colocacao_ou_fase = ? WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, campeonatoEquipe.getIdCampeonato());
            statement.setInt(2, campeonatoEquipe.getIdEquipe());
            statement.setString(3, campeonatoEquipe.getColocacaoOuFase());
            statement.setInt(4, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar campeonato_equipe com id: " + id, e);
        }
    }

    public CampeonatoEquipe obterPorId(Integer id) {
        String sql = "SELECT id, id_campeonato, id_equipe, colocacao_ou_fase FROM public.campeonato_equipe WHERE id = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new CampeonatoEquipe(
                        rs.getInt("id"),
                        rs.getInt("id_campeonato"),
                        rs.getInt("id_equipe"),
                        rs.getString("colocacao_ou_fase")
                );
            } else {
                throw new RuntimeException("CampeonatoEquipe com id " + id + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter campeonato_equipe com id: " + id, e);
        }
    }
}
