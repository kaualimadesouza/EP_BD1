package org.example.goalytics.repository;

import org.example.goalytics.model.Impedimento;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImpedimentoRepository {

    private final DataSource dataSource;

    public ImpedimentoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Impedimento> listarTodos() {
        String sql = "SELECT * FROM public.impedimento;";
        List<Impedimento> impedimentos = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Impedimento impedimento = new Impedimento(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                impedimentos.add(impedimento);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar impedimentos", e);
        }
        return impedimentos;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.impedimento WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do impedimento com num_evento: " + numEvento, e);
        }
    }

    public void excluirImpedimento(Integer numEvento) {
        String sql = "DELETE FROM public.impedimento WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir impedimento com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'impedimento';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela impedimento", e);
        }
        return colunas;
    }

    public void inserir(Impedimento impedimento) {
        String sql = "INSERT INTO public.impedimento (id_partida, id_jogador, x, y, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, impedimento.getIdPartida());
            statement.setInt(2, impedimento.getIdJogador());
            statement.setDouble(3, impedimento.getX());
            statement.setDouble(4, impedimento.getY());
            statement.setString(5, impedimento.getFaseDaPartida());
            statement.setTimestamp(6, impedimento.getCronometragem());
            statement.setInt(7, impedimento.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    impedimento.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir impedimento", e);
        }
    }

    public void atualizar(Impedimento impedimento, Integer numEvento) {
        String sql = "UPDATE public.impedimento SET id_partida = ?, id_jogador = ?, x = ?, y = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, impedimento.getIdPartida());
            statement.setInt(2, impedimento.getIdJogador());
            statement.setDouble(3, impedimento.getX());
            statement.setDouble(4, impedimento.getY());
            statement.setString(5, impedimento.getFaseDaPartida());
            statement.setTimestamp(6, impedimento.getCronometragem());
            statement.setInt(7, impedimento.getIdEventoAnterior());
            statement.setInt(8, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar impedimento com num_evento: " + numEvento, e);
        }
    }

    public Impedimento obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.impedimento WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Impedimento(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador"),
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Impedimento com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter impedimento com num_evento: " + numEvento, e);
        }
    }
}
