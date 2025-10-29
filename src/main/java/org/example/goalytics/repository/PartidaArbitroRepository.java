package org.example.goalytics.repository;

import org.example.goalytics.model.PartidaArbitro;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartidaArbitroRepository {
    private final DataSource dataSource;

    public PartidaArbitroRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PartidaArbitro> listarTodos() {
        String sql = "SELECT * FROM public.partida_arbitro;";
        List<PartidaArbitro> lista = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PartidaArbitro pa = new PartidaArbitro(
                    rs.getInt("id"),
                    rs.getInt("id_partida"),
                    rs.getInt("id_arbitro"),
                    rs.getString("funcao_arbitro")
                );
                lista.add(pa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar partida_arbitro", e);
        }
        return lista;
    }

    public void inserir(PartidaArbitro partidaArbitro) {
        String sql = "INSERT INTO public.partida_arbitro (id_partida, id_arbitro, funcao_arbitro) VALUES (?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partidaArbitro.getIdPartida());
            statement.setInt(2, partidaArbitro.getIdArbitro());
            statement.setString(3, partidaArbitro.getFuncaoArbitro());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir partida_arbitro", e);
        }
    }

    public void atualizar(PartidaArbitro partidaArbitro, Integer id) {
        String sql = "UPDATE public.partida_arbitro SET id_partida = ?, id_arbitro = ?, funcao_arbitro = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partidaArbitro.getIdPartida());
            statement.setInt(2, partidaArbitro.getIdArbitro());
            statement.setString(3, partidaArbitro.getFuncaoArbitro());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar partida_arbitro", e);
        }
    }

    public void excluir(Integer id) {
        String sql = "DELETE FROM public.partida_arbitro WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir partida_arbitro", e);
        }
    }

    public PartidaArbitro obterPorId(Integer id) {
        String sql = "SELECT * FROM public.partida_arbitro WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new PartidaArbitro(
                    rs.getInt("id"),
                    rs.getInt("id_partida"),
                    rs.getInt("id_arbitro"),
                    rs.getString("funcao_arbitro")
                );
            } else {
                throw new RuntimeException("PartidaArbitro não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter partida_arbitro", e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.columns where table_schema = 'public' and table_name = 'partida_arbitro';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela partida_arbitro", e);
        }
        return colunas;
    }
}

