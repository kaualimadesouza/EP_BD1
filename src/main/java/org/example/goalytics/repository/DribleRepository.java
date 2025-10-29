package org.example.goalytics.repository;

import org.example.goalytics.model.Drible;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DribleRepository {

    private final DataSource dataSource;

    public DribleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Drible> listarTodos() {
        String sql = "SELECT * FROM public.drible;";
        List<Drible> dribles = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Drible drible = new Drible(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_executor"),
                        rs.getString("tipo"),
                        rs.getBoolean("sucesso"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("z"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                dribles.add(drible);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar dribles", e);
        }
        return dribles;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.drible WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do drible com num_evento: " + numEvento, e);
        }
    }

    public void excluirDrible(Integer numEvento) {
        String sql = "DELETE FROM public.drible WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir drible com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'drible';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela drible", e);
        }
        return colunas;
    }

    public void inserir(Drible drible) {
        String sql = "INSERT INTO public.drible (id_partida, id_jogador_executor, tipo, sucesso, duracao, x, y, z, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, drible.getIdPartida());
            statement.setInt(2, drible.getIdJogadorExecutor());
            statement.setString(3, drible.getTipo());
            statement.setBoolean(4, drible.getSucesso());
            statement.setTimestamp(5, drible.getDuracao());
            statement.setDouble(6, drible.getX());
            statement.setDouble(7, drible.getY());
            statement.setDouble(8, drible.getZ());
            statement.setString(9, drible.getFaseDaPartida());
            statement.setTimestamp(10, drible.getCronometragem());
            statement.setInt(11, drible.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    drible.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir drible", e);
        }
    }

    public void atualizar(Drible drible, Integer numEvento) {
        String sql = "UPDATE public.drible SET id_partida = ?, id_jogador_executor = ?, tipo = ?, sucesso = ?, duracao = ?, x = ?, y = ?, z = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, drible.getIdPartida());
            statement.setInt(2, drible.getIdJogadorExecutor());
            statement.setString(3, drible.getTipo());
            statement.setBoolean(4, drible.getSucesso());
            statement.setTimestamp(5, drible.getDuracao());
            statement.setDouble(6, drible.getX());
            statement.setDouble(7, drible.getY());
            statement.setDouble(8, drible.getZ());
            statement.setString(9, drible.getFaseDaPartida());
            statement.setTimestamp(10, drible.getCronometragem());
            statement.setInt(11, drible.getIdEventoAnterior());
            statement.setInt(12, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar drible com num_evento: " + numEvento, e);
        }
    }

    public Drible obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.drible WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Drible(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_executor"),
                        rs.getString("tipo"),
                        rs.getBoolean("sucesso"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("z"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Drible com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter drible com num_evento: " + numEvento, e);
        }
    }
}
