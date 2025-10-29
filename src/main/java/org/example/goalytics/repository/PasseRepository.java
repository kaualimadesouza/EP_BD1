package org.example.goalytics.repository;

import org.example.goalytics.model.Passe;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PasseRepository {

    private final DataSource dataSource;

    public PasseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Passe> listarTodos() {
        String sql = "SELECT * FROM public.passe;";
        List<Passe> passes = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Passe passe = new Passe(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_origem"),
                        rs.getInt("id_jogador_destino"),
                        rs.getBoolean("sucesso"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("posicao_x_origem"),
                        rs.getDouble("posicao_y_origem"),
                        rs.getDouble("posicao_z_origem"),
                        rs.getDouble("posicao_x_destino"),
                        rs.getDouble("posicao_y_destino"),
                        rs.getDouble("posicao_z_destino"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
                passes.add(passe);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar passes", e);
        }
        return passes;
    }

    public boolean existePorNumEvento(Integer numEvento) {
        String sql = "SELECT 1 FROM public.passe WHERE num_evento = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existencia do passe com num_evento: " + numEvento, e);
        }
    }

    public void excluirPasse(Integer numEvento) {
        String sql = "DELETE FROM public.passe WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir passe com num_evento: " + numEvento, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'passe';";
        List<String> colunas = new ArrayList<>();

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela passe", e);
        }
        return colunas;
    }

    public void inserir(Passe passe) {
        String sql = "INSERT INTO public.passe (id_partida, id_jogador_origem, id_jogador_destino, sucesso, duracao, posicao_x_origem, posicao_y_origem, posicao_z_origem, posicao_x_destino, posicao_y_destino, posicao_z_destino, fase_da_partida, cronometragem, id_evento_anterior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, passe.getIdPartida());
            statement.setInt(2, passe.getIdJogadorOrigem());
            statement.setInt(3, passe.getIdJogadorDestino());
            statement.setBoolean(4, passe.getSucesso());
            statement.setTimestamp(5, passe.getDuracao());
            statement.setDouble(6, passe.getPosicaoXOrigem());
            statement.setDouble(7, passe.getPosicaoYOrigem());
            statement.setDouble(8, passe.getPosicaoZOrigem());
            statement.setDouble(9, passe.getPosicaoXDestino());
            statement.setDouble(10, passe.getPosicaoYDestino());
            statement.setDouble(11, passe.getPosicaoZDestino());
            statement.setString(12, passe.getFaseDaPartida());
            statement.setTimestamp(13, passe.getCronometragem());
            statement.setInt(14, passe.getIdEventoAnterior());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    passe.setNumEvento(generatedKeys.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir passe", e);
        }
    }

    public void atualizar(Passe passe, Integer numEvento) {
        String sql = "UPDATE public.passe SET id_partida = ?, id_jogador_origem = ?, id_jogador_destino = ?, sucesso = ?, duracao = ?, posicao_x_origem = ?, posicao_y_origem = ?, posicao_z_origem = ?, posicao_x_destino = ?, posicao_y_destino = ?, posicao_z_destino = ?, fase_da_partida = ?, cronometragem = ?, id_evento_anterior = ? WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, passe.getIdPartida());
            statement.setInt(2, passe.getIdJogadorOrigem());
            statement.setInt(3, passe.getIdJogadorDestino());
            statement.setBoolean(4, passe.getSucesso());
            statement.setTimestamp(5, passe.getDuracao());
            statement.setDouble(6, passe.getPosicaoXOrigem());
            statement.setDouble(7, passe.getPosicaoYOrigem());
            statement.setDouble(8, passe.getPosicaoZOrigem());
            statement.setDouble(9, passe.getPosicaoXDestino());
            statement.setDouble(10, passe.getPosicaoYDestino());
            statement.setDouble(11, passe.getPosicaoZDestino());
            statement.setString(12, passe.getFaseDaPartida());
            statement.setTimestamp(13, passe.getCronometragem());
            statement.setInt(14, passe.getIdEventoAnterior());
            statement.setInt(15, numEvento);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar passe com num_evento: " + numEvento, e);
        }
    }

    public Passe obterPorNumEvento(Integer numEvento) {
        String sql = "SELECT * FROM public.passe WHERE num_evento = ?;";

        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, numEvento);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Passe(
                        rs.getInt("num_evento"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_jogador_origem"),
                        rs.getInt("id_jogador_destino"),
                        rs.getBoolean("sucesso"),
                        rs.getTimestamp("duracao"),
                        rs.getDouble("posicao_x_origem"),
                        rs.getDouble("posicao_y_origem"),
                        rs.getDouble("posicao_z_origem"),
                        rs.getDouble("posicao_x_destino"),
                        rs.getDouble("posicao_y_destino"),
                        rs.getDouble("posicao_z_destino"),
                        rs.getString("fase_da_partida"),
                        rs.getTimestamp("cronometragem"),
                        rs.getInt("id_evento_anterior")
                );
            } else {
                throw new RuntimeException("Passe com num_evento " + numEvento + " não encontrado.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao obter passe com num_evento: " + numEvento, e);
        }
    }
}
