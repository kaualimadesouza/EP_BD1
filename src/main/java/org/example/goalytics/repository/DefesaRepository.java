package org.example.goalytics.repository;

import org.example.goalytics.model.Defesa;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DefesaRepository {

    private final DataSource dataSource;

    public DefesaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Defesa> listarTodos() {
        String sql = "SELECT * FROM public.defesa;";
        List<Defesa> defesas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Defesa defesa = new Defesa(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getString("tipo"),
                        rs.getInt("id_defensor"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                defesas.add(defesa);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar defesas", e);
        }
        return defesas;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.defesa WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia da defesa com num_evento: " + numEvento, e);
        }
    }

    public void excluirDefesa(Integer numEvento) {
        String sql = "DELETE FROM public.defesa WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir defesa com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'defesa';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela defesa", e);
        }
        return colunas;
    }

    public void inserir(Defesa defesa) {
        String sql = "INSERT INTO public.defesa (id_partida, tipo, id_defensor, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, defesa.getIdPartida());
            statement.setString(2, defesa.getTipo());
            statement.setInt(3, defesa.getIdDefensor());
            statement.setString(4, defesa.getFaseDaPartida());
            statement.setTimestamp(5, defesa.getCronometragem());
            statement.setInt(6, defesa.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    defesa.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir defesa", e);
        }
    }

    public void atualizar(Defesa defesa, Integer numEvento) {
        String sql = "UPDATE public.defesa SET id_partida = ?, tipo = ?, id_defensor = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, defesa.getIdPartida());
            statement.setString(2, defesa.getTipo());
            statement.setInt(3, defesa.getIdDefensor());
            statement.setString(4, defesa.getFaseDaPartida());
            statement.setTimestamp(5, defesa.getCronometragem());
            statement.setInt(6, defesa.getIdEventoAnterior());
            statement.setInt(7, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar defesa com num_evento: " + numEvento, e);
        }
    }

    public Defesa obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.defesa WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Defesa(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getString("tipo"),
                        rs.getInt("id_defensor"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Defesa com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter defesa com num_evento: " + numEvento, e);
        }
    }
}
