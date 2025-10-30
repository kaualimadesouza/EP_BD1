package org.example.goalytics.repository;

import org.example.goalytics.Records.PartidaDetalhesDTO;
import org.example.goalytics.model.Partida;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartidaRepository {
    private final DataSource dataSource;

    public PartidaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Partida> listarTodos() {
        String sql = "SELECT * FROM public.partida;";
        List<Partida> partidas = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Partida partida = new Partida(
                        rs.getInt("id"),
                        rs.getInt("id_estadio"),
                        rs.getInt("id_campeonato"),
                        rs.getDate("data"),
                        rs.getTime("horario"),
                        rs.getString("condicao_climatica"),
                        rs.getString("status")
                );
                partidas.add(partida);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar partidas", e);
        }
        return partidas;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.partida WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência da partida com id: " + id, e);
        }
    }

    public void excluirPartida(Integer id) {
        String sql = "DELETE FROM public.partida WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir partida com id: " + id, e);
        }
    }

    public void inserir(Partida partida) {
        String sql = "INSERT INTO public.partida (id_estadio, id_campeonato, data, horario, condicao_climatica, status) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partida.getIdEstadio());
            statement.setInt(2, partida.getIdCampeonato());
            statement.setDate(3, partida.getData() != null ? new java.sql.Date(partida.getData().getTime()) : null);
            statement.setTime(4, partida.getHorario());
            statement.setString(5, partida.getCondicaoClimatica());
            statement.setString(6, partida.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir partida", e);
        }
    }

    public void atualizar(Partida partida, Integer id) {
        String sql = "UPDATE public.partida SET id_estadio = ?, id_campeonato = ?, data = ?, horario = ?, condicao_climatica = ?, status = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, partida.getIdEstadio());
            statement.setInt(2, partida.getIdCampeonato());
            statement.setDate(3, partida.getData() != null ? new java.sql.Date(partida.getData().getTime()) : null);
            statement.setTime(4, partida.getHorario());
            statement.setString(5, partida.getCondicaoClimatica());
            statement.setString(6, partida.getStatus());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar partida com id: " + id, e);
        }
    }

    public Partida obterPorId(Integer id) {
        String sql = "SELECT * FROM public.partida WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Partida(
                        rs.getInt("id"),
                        rs.getInt("id_estadio"),
                        rs.getInt("id_campeonato"),
                        rs.getDate("data"),
                        rs.getTime("horario"),
                        rs.getString("condicao_climatica"),
                        rs.getString("status")
                );
            } else {
                throw new RuntimeException("Partida com id " + id + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter partida com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'partida';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela partida", e);
        }
        return colunas;
    }

    public List<PartidaDetalhesDTO> obterUltimaPartidaDetalhes() {
        String sql = "select \n" +
                "\tp.id,\n" +
                "    p.data,\n" +
                "    p.horario,\n" +
                "    p.condicao_climatica,\n" +
                "    p.status,\n" +
                "    c.temporada as \"temporada_campeonato\",\n" +
                "    c.campeao,\n" +
                "    c.nome as \"nome_campeonato\",\n" +
                "    e.nome_oficial as \"nome_oficial_estadio\",\n" +
                "    e.nome_apelido as \"nome_apelido_estadio\",\n" +
                "    e.pais\n" +
                "from public.partida p\n" +
                "inner join public.campeonato c on p.id_campeonato = c.id\n" +
                "inner join public.estadio e on p.id_estadio = e.id\n" +
                "limit 10;\n";
        List<PartidaDetalhesDTO> partidasDetalhasdas = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PartidaDetalhesDTO partidaDetalhes = new PartidaDetalhesDTO(
                        rs.getLong("id"),
                        rs.getDate("data"),
                        rs.getTime("horario"),
                        rs.getString("condicao_climatica"),
                        rs.getString("status"),
                        rs.getInt("temporada_campeonato"),
                        rs.getString("campeao"),
                        rs.getString("nome_campeonato"),
                        rs.getString("nome_oficial_estadio"),
                        rs.getString("nome_apelido_estadio"),
                        rs.getString("pais")
                );
                partidasDetalhasdas.add(partidaDetalhes);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return partidasDetalhasdas;
    }
}
