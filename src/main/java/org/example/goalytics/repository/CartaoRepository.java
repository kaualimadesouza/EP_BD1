package org.example.goalytics.repository;

import org.example.goalytics.model.Cartao;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartaoRepository {

    private final DataSource dataSource;

    public CartaoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Cartao> listarTodos() {
        String sql = "SELECT * FROM public.cartao;";
        List<Cartao> cartoes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Cartao cartao = new Cartao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_pessoa"),
                        rs.getString("tipo"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometro"),
                        rs.getInt("id_evento_anterior")
                );
                cartoes.add(cartao);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cartoes", e);
        }
        return cartoes;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.cartao WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do cartao com num_evento: " + numEvento, e);
        }
    }

    public void excluirCartao(Integer numEvento) {
        String sql = "DELETE FROM public.cartao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cartao com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'cartao';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela cartao", e);
        }
        return colunas;
    }

    public void inserir(Cartao cartao) {
        String sql = "INSERT INTO public.cartao (id_partida, id_pessoa, tipo, fase_da_partida, cronometro, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cartao.getIdPartida());
            statement.setInt(2, cartao.getIdPessoa());
            statement.setString(3, cartao.getTipo());
            statement.setString(4, cartao.getFaseDaPartida());
            statement.setTimestamp(5, cartao.getCronometro());
            statement.setInt(6, cartao.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cartao.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cartao", e);
        }
    }

    public void atualizar(Cartao cartao, Integer numEvento) {
        String sql = "UPDATE public.cartao SET id_partida = ?, id_pessoa = ?, tipo = ?, fase_da_partida = ?, cronometro = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cartao.getIdPartida());
            statement.setInt(2, cartao.getIdPessoa());
            statement.setString(3, cartao.getTipo());
            statement.setString(4, cartao.getFaseDaPartida());
            statement.setTimestamp(5, cartao.getCronometro());
            statement.setInt(6, cartao.getIdEventoAnterior());
            statement.setInt(7, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cartao com num_evento: " + numEvento, e);
        }
    }

    public Cartao obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.cartao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Cartao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_pessoa"),
                        rs.getString("tipo"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometro"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Cartao com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter cartao com num_evento: " + numEvento, e);
        }
    }
}
