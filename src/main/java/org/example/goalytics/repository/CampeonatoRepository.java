package org.example.goalytics.repository;

import org.example.goalytics.Records.EstadiosNaoEstaNoCampeonatoDTO;
import org.example.goalytics.Records.EstatisticasCampeonatoDTO;
import org.example.goalytics.Records.MediaGolsPorPartidaEmCampeonatoDTO;
import org.example.goalytics.model.Campeonato;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampeonatoRepository {
    private final DataSource dataSource;

    public CampeonatoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Campeonato> listarTodos() {
        String sql = "SELECT * FROM public.campeonato;";
        List<Campeonato> campeonatos = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Campeonato campeonato = new Campeonato(
                        rs.getInt("id"),
                        rs.getObject("temporada") != null ? rs.getInt("temporada") : null,
                        rs.getString("tipo_campeonato"),
                        rs.getString("status"),
                        rs.getString("campeao"),
                        rs.getString("nome"),
                        rs.getString("url_campeonato"),
                        rs.getString("regiao")
                );
                campeonatos.add(campeonato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar campeonatos", e);
        }
        return campeonatos;
    }

    public boolean existePorId(Integer id) {
        String sql = "SELECT 1 FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do campeonato com id: " + id, e);
        }
    }

    public void excluirCampeonato(Integer id) {
        String sql = "DELETE FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir campeonato com id: " + id, e);
        }
    }

    public void inserir(Campeonato campeonato) {
        String sql = "INSERT INTO public.campeonato (temporada, tipo_campeonato, status, campeao, nome) VALUES (?, ?, ?, ?, ?);";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (campeonato.getTemporada() != null) {
                statement.setInt(1, campeonato.getTemporada());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, campeonato.getTipoCampeonato());
            statement.setString(3, campeonato.getStatus());
            statement.setString(4, campeonato.getCampeao());
            statement.setString(5, campeonato.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir campeonato", e);
        }
    }

    public void atualizar(Campeonato campeonato, Integer id) {
        String sql = "UPDATE public.campeonato SET temporada = ?, tipo_campeonato = ?, status = ?, campeao = ?, nome = ? WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (campeonato.getTemporada() != null) {
                statement.setInt(1, campeonato.getTemporada());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, campeonato.getTipoCampeonato());
            statement.setString(3, campeonato.getStatus());
            statement.setString(4, campeonato.getCampeao());
            statement.setString(5, campeonato.getNome());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar campeonato com id: " + id, e);
        }
    }

    public Campeonato obterPorId(Integer id) {
        String sql = "SELECT * FROM public.campeonato WHERE id = ?;";
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Campeonato(
                        rs.getInt("id"),
                        rs.getObject("temporada") != null ? rs.getInt("temporada") : null,
                        rs.getString("tipo_campeonato"),
                        rs.getString("status"),
                        rs.getString("campeao"),
                        rs.getString("nome"),
                        rs.getString("url_campeonato"),
                        rs.getString("regiao")
                );
            } else {
                throw new RuntimeException("Campeonato com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter campeonato com id: " + id, e);
        }
    }

    public List<String> listarNomeColunas() {
        String sql = "select column_name from information_schema.\"columns\" c where c.table_schema = 'public' and c.table_name = 'campeonato';";
        List<String> colunas = new ArrayList<>();
        try(Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                colunas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar colunas da tabela campeonato", e);
        }
        return colunas;
    }

    public List<MediaGolsPorPartidaEmCampeonatoDTO> obterMediaGolsPorPartidaCampeonatos() {
        String query = "SELECT\n" +
                "    c.nome AS nome_campeonato,\n" +
                "    AVG(total_placar_partida) AS media_gols_por_jogo\n" +
                "FROM\n" +
                "    campeonato c\n" +
                "JOIN\n" +
                "    partida p ON c.id = p.id_campeonato\n" +
                "JOIN\n" +
                "    (\n" +
                "        SELECT\n" +
                "            id_partida,\n" +
                "            SUM(placar) AS total_placar_partida\n" +
                "        FROM\n" +
                "            partida_equipe_possui\n" +
                "        GROUP BY\n" +
                "            id_partida\n" +
                "    ) AS placares_partida ON p.id = placares_partida.id_partida\n" +
                "GROUP BY\n" +
                "    c.nome\n" +
                "ORDER BY\n" +
                "    media_gols_por_jogo DESC;";

        List<MediaGolsPorPartidaEmCampeonatoDTO> resultados = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MediaGolsPorPartidaEmCampeonatoDTO dto = new MediaGolsPorPartidaEmCampeonatoDTO(
                        rs.getString("nome_campeonato"),
                        rs.getDouble("media_gols_por_jogo")
                );
                resultados.add(dto);
            }

            return resultados;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter média de gols por partida em campeonatos", e);
        }
    }

    public List<EstadiosNaoEstaNoCampeonatoDTO> obterEstadiosNaoEstaNoCampeonato(Integer id) {
        String query = "SELECT \n" +
                "    e.nome_oficial,\n" +
                "    e.endereco,\n" +
                "    e.capacidade_atual \n" +
                "FROM estadio e\n" +
                "except\n" +
                "SELECT  \n" +
                "    e2.nome_oficial,\n" +
                "    e2.endereco,\n" +
                "    e2.capacidade_atual\n" +
                "FROM estadio e2\n" +
                "JOIN partida p ON p.id_estadio = e2.id\n" +
                "JOIN campeonato c ON c.id = p.id_campeonato\n" +
                "WHERE c.id = ?\n" +
                "order by capacidade_atual desc;\n";
        List<EstadiosNaoEstaNoCampeonatoDTO> resultados = new ArrayList<>();
        try (Connection conn = this.dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EstadiosNaoEstaNoCampeonatoDTO dto = new EstadiosNaoEstaNoCampeonatoDTO(
                        rs.getString("nome_oficial"),
                        rs.getString("endereco"),
                        rs.getInt("capacidade_atual")
                );
                resultados.add(dto);
            }

            return resultados;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter estádios que não estão no campeonato");
        }
    }
}

