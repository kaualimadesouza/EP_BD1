package org.example.goalytics.repository;

import org.example.goalytics.model.Substituicao;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubstituicaoRepository {

    private final DataSource dataSource;

    public SubstituicaoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Substituicao> listarTodos() {
        String sql = "SELECT * FROM public.substituicao;";
        List<Substituicao> substituicoes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Substituicao substituicao = new Substituicao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_pessoa_sai"),
                        rs.getInt("id_pessoa_entra"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                substituicoes.add(substituicao);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar substituicoes", e);
        }
        return substituicoes;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.substituicao WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia da substituicao com num_evento: " + numEvento, e);
        }
    }

    public void excluirSubstituicao(Integer numEvento) {
        String sql = "DELETE FROM public.substituicao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir substituicao com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'substituicao';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela substituicao", e);
        }
        return colunas;
    }

    public void inserir(Substituicao substituicao) {
        String sql = "INSERT INTO public.substituicao (id_partida, id_pessoa_sai, id_pessoa_entra, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, substituicao.getIdPartida());
            statement.setInt(2, substituicao.getIdPessoaSai());
            statement.setInt(3, substituicao.getIdPessoaEntra());
            statement.setString(4, substituicao.getFaseDaPartida());
            statement.setTimestamp(5, substituicao.getCronometragem());
            statement.setInt(6, substituicao.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    substituicao.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir substituicao", e);
        }
    }

    public void atualizar(Substituicao substituicao, Integer numEvento) {
        String sql = "UPDATE public.substituicao SET id_partida = ?, id_pessoa_sai = ?, id_pessoa_entra = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, substituicao.getIdPartida());
            statement.setInt(2, substituicao.getIdPessoaSai());
            statement.setInt(3, substituicao.getIdPessoaEntra());
            statement.setString(4, substituicao.getFaseDaPartida());
            statement.setTimestamp(5, substituicao.getCronometragem());
            statement.setInt(6, substituicao.getIdEventoAnterior());
            statement.setInt(7, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar substituicao com num_evento: " + numEvento, e);
        }
    }

    public Substituicao obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.substituicao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Substituicao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_pessoa_sai"),
                        rs.getInt("id_pessoa_entra"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Substituicao com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter substituicao com num_evento: " + numEvento, e);
        }
    }
}
