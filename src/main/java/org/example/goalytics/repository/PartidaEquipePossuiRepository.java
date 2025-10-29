package org.example.goalytics.repository;

import org.example.goalytics.model.PartidaEquipePossui;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartidaEquipePossuiRepository {
    private final DataSource dataSource;

    public PartidaEquipePossuiRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PartidaEquipePossui> listarTodos() {
        String sql = "SELECT * FROM public.partida_equipe_possui;";
        List<PartidaEquipePossui> lista = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PartidaEquipePossui pep = new PartidaEquipePossui(
                    rs.getInt("id"),
                    rs.getInt("id_partida"),
                    rs.getInt("id_equipe"),
                    rs.getInt("placar")
                );
                lista.add(pep);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar partida_equipe_possui", e);
        }
        return lista;
    }

    public void inserir(PartidaEquipePossui partidaEquipePossui) {
        String sql = "INSERT INTO public.partida_equipe_possui (id_partida, id_equipe, placar) VALUES (?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partidaEquipePossui.getIdPartida());
            statement.setInt(2, partidaEquipePossui.getIdEquipe());
            statement.setInt(3, partidaEquipePossui.getPlacar());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir partida_equipe_possui", e);
        }
    }

    public void atualizar(PartidaEquipePossui partidaEquipePossui, Integer id) {
        String sql = "UPDATE public.partida_equipe_possui SET id_partida = ?, id_equipe = ?, placar = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partidaEquipePossui.getIdPartida());
            statement.setInt(2, partidaEquipePossui.getIdEquipe());
            statement.setInt(3, partidaEquipePossui.getPlacar());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar partida_equipe_possui", e);
        }
    }

    public void excluir(Integer id) {
        String sql = "DELETE FROM public.partida_equipe_possui WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir partida_equipe_possui", e);
        }
    }

    public PartidaEquipePossui obterPorId(Integer id) {
        String sql = "SELECT * FROM public.partida_equipe_possui WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new PartidaEquipePossui(
                    rs.getInt("id"),
                    rs.getInt("id_partida"),
                    rs.getInt("id_equipe"),
                    rs.getInt("placar")
                );
            } else {
                throw new RuntimeException("PartidaEquipePossui não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter partida_equipe_possui", e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.columns where table_schema = 'public' and table_name = 'partida_equipe_possui';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela partida_equipe_possui", e);
        }
        return colunas;
    }
}
