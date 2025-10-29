package org.example.goalytics.repository;

import org.example.goalytics.model.Finalizacao;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinalizacaoRepository {

    private final DataSource dataSource;

    public FinalizacaoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Finalizacao> listarTodos() {
        String sql = "SELECT * FROM public.finalizacao;";
        List<Finalizacao> finalizacoes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Finalizacao finalizacao = new Finalizacao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador"),
                        rs.getBoolean("sucesso"),
                        rs.getString("parte_do_corpo"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("posicao_x_origem"),
                        rs.getDouble("posicao_y_origem"),
                        rs.getDouble("posicao_z_origem"),
                        rs.getDouble("posicao_x_destino"),
                        rs.getDouble("posicao_y_destino"),
                        rs.getDouble("posicao_z_destino"),
                        rs.getString("fase_da_partida"),
                        rs.getTime("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                finalizacoes.add(finalizacao);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar finalizacoes", e);
        }
        return finalizacoes;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.finalizacao WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia da finalizacao com num_evento: " + numEvento, e);
        }
    }

    public void excluirFinalizacao(Integer numEvento) {
        String sql = "DELETE FROM public.finalizacao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir finalizacao com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'finalizacao';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela finalizacao", e);
        }
        return colunas;
    }

    public void inserir(Finalizacao finalizacao) {
        String sql = "INSERT INTO public.finalizacao (id_partida, id_jogador, sucesso, parte_do_corpo, duracao, posicao_x_origem, posicao_y_origem, posicao_z_origem, posicao_x_destino, posicao_y_destino, posicao_z_destino, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, finalizacao.getIdPartida());
            statement.setInt(2, finalizacao.getIdJogador());
            statement.setBoolean(3, finalizacao.getSucesso());
            statement.setString(4, finalizacao.getParteDoCorpo());
            statement.setTimestamp(5, finalizacao.getDuracao());
            statement.setDouble(6, finalizacao.getPosicaoXOrigem());
            statement.setDouble(7, finalizacao.getPosicaoYOrigem());
            statement.setDouble(8, finalizacao.getPosicaoZOrigem());
            statement.setDouble(9, finalizacao.getPosicaoXDestino());
            statement.setDouble(10, finalizacao.getPosicaoYDestino());
            statement.setDouble(11, finalizacao.getPosicaoZDestino());
            statement.setString(12, finalizacao.getFaseDaPartida());
            statement.setTime(13, finalizacao.getCronometragem());
            statement.setInt(14, finalizacao.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    finalizacao.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir finalizacao", e);
        }
    }

    public void atualizar(Finalizacao finalizacao, Integer numEvento) {
        String sql = "UPDATE public.finalizacao SET id_partida = ?, id_jogador = ?, sucesso = ?, parte_do_corpo = ?, duracao = ?, posicao_x_origem = ?, posicao_y_origem = ?, posicao_z_origem = ?, posicao_x_destino = ?, posicao_y_destino = ?, posicao_z_destino = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, finalizacao.getIdPartida());
            statement.setInt(2, finalizacao.getIdJogador());
            statement.setBoolean(3, finalizacao.getSucesso());
            statement.setString(4, finalizacao.getParteDoCorpo());
            statement.setTimestamp(5, finalizacao.getDuracao());
            statement.setDouble(6, finalizacao.getPosicaoXOrigem());
            statement.setDouble(7, finalizacao.getPosicaoYOrigem());
            statement.setDouble(8, finalizacao.getPosicaoZOrigem());
            statement.setDouble(9, finalizacao.getPosicaoXDestino());
            statement.setDouble(10, finalizacao.getPosicaoYDestino());
            statement.setDouble(11, finalizacao.getPosicaoZDestino());
            statement.setString(12, finalizacao.getFaseDaPartida());
            statement.setTime(13, finalizacao.getCronometragem());
            statement.setInt(14, finalizacao.getIdEventoAnterior());
            statement.setInt(15, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar finalizacao com num_evento: " + numEvento, e);
        }
    }

    public Finalizacao obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.finalizacao WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Finalizacao(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador"),
                        rs.getBoolean("sucesso"),
                        rs.getString("parte_do_corpo"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("posicao_x_origem"),
                        rs.getDouble("posicao_y_origem"),
                        rs.getDouble("posicao_z_origem"),
                        rs.getDouble("posicao_x_destino"),
                        rs.getDouble("posicao_y_destino"),
                        rs.getDouble("posicao_z_destino"),
                        rs.getString("fase_da_partida"),
                        rs.getTime("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Finalizacao com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter finalizacao com num_evento: " + numEvento, e);
        }
    }
}
