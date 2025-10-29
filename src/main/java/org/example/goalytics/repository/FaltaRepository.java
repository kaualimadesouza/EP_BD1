package org.example.goalytics.repository;

import org.example.goalytics.model.Falta;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FaltaRepository {

    private final DataSource dataSource;

    public FaltaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Falta> listarTodos() {
        String sql = "SELECT * FROM public.falta;";
        List<Falta> faltas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Falta falta = new Falta(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_vitima"),
                        rs.getInt("id_jogador_infrator"),
                        rs.getString("tipo"),
                        rs.getString("local"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                faltas.add(falta);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar faltas", e);
        }
        return faltas;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.falta WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia da falta com num_evento: " + numEvento, e);
        }
    }

    public void excluirFalta(Integer numEvento) {
        String sql = "DELETE FROM public.falta WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir falta com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'falta';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela falta", e);
        }
        return colunas;
    }

    public void inserir(Falta falta) {
        String sql = "INSERT INTO public.falta (id_partida, id_jogador_vitima, id_jogador_infrator, tipo, local, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, falta.getIdPartida());
            statement.setInt(2, falta.getIdJogadorVitima());
            statement.setInt(3, falta.getIdJogadorInfrator());
            statement.setString(4, falta.getTipo());
            statement.setString(5, falta.getLocal());
            statement.setString(6, falta.getFaseDaPartida());
            statement.setTimestamp(7, falta.getCronometragem());
            statement.setInt(8, falta.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    falta.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir falta", e);
        }
    }

    public void atualizar(Falta falta, Integer numEvento) {
        String sql = "UPDATE public.falta SET id_partida = ?, id_jogador_vitima = ?, id_jogador_infrator = ?, tipo = ?, local = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, falta.getIdPartida());
            statement.setInt(2, falta.getIdJogadorVitima());
            statement.setInt(3, falta.getIdJogadorInfrator());
            statement.setString(4, falta.getTipo());
            statement.setString(5, falta.getLocal());
            statement.setString(6, falta.getFaseDaPartida());
            statement.setTimestamp(7, falta.getCronometragem());
            statement.setInt(8, falta.getIdEventoAnterior());
            statement.setInt(9, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar falta com num_evento: " + numEvento, e);
        }
    }

    public Falta obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.falta WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Falta(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_vitima"),
                        rs.getInt("id_jogador_infrator"),
                        rs.getString("tipo"),
                        rs.getString("local"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Falta com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter falta com num_evento: " + numEvento, e);
        }
    }
}
