package org.example.goalytics.repository;

import org.example.goalytics.model.Escanteio;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EscanteioRepository {

    private final DataSource dataSource;

    public EscanteioRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Escanteio> listarTodos() {
        String sql = "SELECT * FROM public.escanteio;";
        List<Escanteio> escanteios = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Escanteio escanteio = new Escanteio(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getString("lado"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                escanteios.add(escanteio);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar escanteios", e);
        }
        return escanteios;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.escanteio WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do escanteio com num_evento: " + numEvento, e);
        }
    }

    public void excluirEscanteio(Integer numEvento) {
        String sql = "DELETE FROM public.escanteio WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir escanteio com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'escanteio';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela escanteio", e);
        }
        return colunas;
    }

    public void inserir(Escanteio escanteio) {
        String sql = "INSERT INTO public.escanteio (id_partida, lado, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, escanteio.getIdPartida());
            statement.setString(2, escanteio.getLado());
            statement.setString(3, escanteio.getFaseDaPartida());
            statement.setTimestamp(4, escanteio.getCronometragem());
            statement.setInt(5, escanteio.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    escanteio.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir escanteio", e);
        }
    }

    public void atualizar(Escanteio escanteio, Integer numEvento) {
        String sql = "UPDATE public.escanteio SET id_partida = ?, lado = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, escanteio.getIdPartida());
            statement.setString(2, escanteio.getLado());
            statement.setString(3, escanteio.getFaseDaPartida());
            statement.setTimestamp(4, escanteio.getCronometragem());
            statement.setInt(5, escanteio.getIdEventoAnterior());
            statement.setInt(6, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar escanteio com num_evento: " + numEvento, e);
        }
    }

    public Escanteio obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.escanteio WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Escanteio(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getString("lado"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Escanteio com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter escanteio com num_evento: " + numEvento, e);
        }
    }
}
