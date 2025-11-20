package org.example.goalytics.service;

import org.example.goalytics.Records.*;
import org.example.goalytics.Records.EquipeJogoHistoricoDTO;
import org.example.goalytics.model.Campeonato;
import org.example.goalytics.model.Equipe;
import org.example.goalytics.model.Jogador;
import org.example.goalytics.model.Partida;
import org.example.goalytics.repository.CampeonatoRepository;
import org.example.goalytics.repository.JogadorRepository;
import org.example.goalytics.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampeonatoService {
    private final CampeonatoRepository campeonatoRepository;
    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository, PartidaRepository partidaRepository, JogadorRepository jogadorRepository) {
        this.campeonatoRepository = campeonatoRepository;
        this.partidaRepository = partidaRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public List<Campeonato> listarCampeonatos() {
        return campeonatoRepository.listarTodos();
    }

    public void inserirCampeonato(Campeonato campeonato) {
        campeonatoRepository.inserir(campeonato);
    }

    public void atualizarCampeonato(Campeonato campeonato, Integer id) {
        if (campeonatoRepository.existePorId(id)) {
            campeonatoRepository.atualizar(campeonato, id);
        } else {
            throw new RuntimeException("Campeonato com id " + id + " não encontrado.");
        }
    }

    public void excluirCampeonato(Integer id) {
        if (!campeonatoRepository.existePorId(id)) {
            throw new RuntimeException("Campeonato com id " + id + " não encontrado.");
        }
        campeonatoRepository.excluirCampeonato(id);
    }

    public Campeonato obterCampeonatoPorId(Integer id) {
        return campeonatoRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return campeonatoRepository.listarNomeColunas();
    }

    public List<CampeonatoHistoricoPartidasDTO> listarCampeonatosComJogosRecentes() {
        // Inicializar a lista de DTOs a serem retornados
        List<CampeonatoHistoricoPartidasDTO> campeonatosComJogosRecentes = new ArrayList<CampeonatoHistoricoPartidasDTO>();

        // 1. Buscar todos os campeonatos
        List<Campeonato> campeonatos = campeonatoRepository.listarTodos();

        // 2. Para cada campeonato, buscar os jogos recentes e montar o DTO
        for (Campeonato campeonato : campeonatos) {
            List<Partida> jogosRecentes = partidaRepository.listarJogosPorCampeonato(campeonato.getId());
            CampeonatoHistoricoPartidasDTO campeonatoHistoricoPartidasDTO = new CampeonatoHistoricoPartidasDTO(
                campeonato.getId(),
                campeonato.getRegiao(),
                campeonato.getUrlCampeonato(),
                campeonato.getNome(),
                new ArrayList<>()
            );
            for (Partida partida : jogosRecentes) {
                List<EquipeHistoricoPartidasDTO> equipes = partidaRepository.obterEquipesPorPartidaHistoricoPartidas(partida.getId());
                if (equipes.size() != 2) {
                    throw new RuntimeException("Número inválido de equipes para a partida com id: " + partida.getId());
                }

                EquipeJogoHistoricoDTO equipeJogoDTO = new EquipeJogoHistoricoDTO(
                    equipes.get(0),
                    equipes.get(1)
                );

                PartidaHistoricoPartidasDTO partidaDTO = new PartidaHistoricoPartidasDTO(
                    partida.getId(),
                    partida.getData(),
                    partida.getHorario(),
                    equipeJogoDTO
                );

                campeonatoHistoricoPartidasDTO.ultimosJogos().add(partidaDTO);
            }
            campeonatosComJogosRecentes.add(campeonatoHistoricoPartidasDTO);
        }

        return campeonatosComJogosRecentes;
    }

    public List<ArtilheirosCampeonatoDTO> listarArtilheiroPorCampeonato() {
        // Inicializar a lista de DTOs a serem retornados
        List<ArtilheirosCampeonatoDTO> artilheirosPorCampeonato = new ArrayList<ArtilheirosCampeonatoDTO>();

        // 1. Buscar todos os campeonatos
        List<Campeonato> campeonatos = campeonatoRepository.listarTodos();

        // 2. Para cada campeonato, buscar os artilheiros e montar o DTO
        for (Campeonato campeonato : campeonatos) {
            List<ArtilheiroDTO> artilheiroDTOS = jogadorRepository.obterArtilheirosPorCampeonato(campeonato.getId());
            ArtilheirosCampeonatoDTO artilheirosCampeonatoDTO = new ArtilheirosCampeonatoDTO(
                campeonato.getNome(),
                artilheiroDTOS
            );
            artilheirosPorCampeonato.add(artilheirosCampeonatoDTO);
        }

        return artilheirosPorCampeonato;
    }


    public EstatisticasCampeonatoDTO buscarEstatisticasPorNomeCampeonato(Integer id) {
        // 0. Buscar todos os campeonatos
        List<Campeonato> campeonatos = campeonatoRepository.listarTodos();

        // 1. Media de gols por partida na competicao
        List<MediaGolsPorPartidaEmCampeonatoDTO> mediaGolsPorPartidaEmCampeonatoDTOS = campeonatoRepository.obterMediaGolsPorPartidaCampeonatos();

        // 2. Estádios que não pertencem à competição
        List<EstadiosNaoEstaNoCampeonatoDTO> estadiosNaoEstaNoCampeonatoDTOS = campeonatoRepository.obterEstadiosNaoEstaNoCampeonato(id);

        return new EstatisticasCampeonatoDTO(
            mediaGolsPorPartidaEmCampeonatoDTOS,
            estadiosNaoEstaNoCampeonatoDTOS
        );
    }

    public List<CampeonatoJogadoresCarosDTO> buscarEstatisticasJogadoresMaisCaros() {
        // 1. Buscar todos os campeonatos
        List<Campeonato> campeonatos = campeonatoRepository.listarTodos();

        // 2. Inicializar a lista de DTOs a serem retornados
        List<CampeonatoJogadoresCarosDTO> campeonatoJogadoresCarosDTOs = new ArrayList<CampeonatoJogadoresCarosDTO>();

        for (Campeonato campeonato : campeonatos) {
            List<JogadoresMaisCarosPorCompeticao> jogadoresMaisCarosPorCompeticaos = jogadorRepository.obterJogadoresMaisCarosPorCampeonato(campeonato.getId());

            CampeonatoJogadoresCarosDTO campeonatoJogadoresCaros = new CampeonatoJogadoresCarosDTO(
                campeonato.getNome(),
                jogadoresMaisCarosPorCompeticaos
            );
            campeonatoJogadoresCarosDTOs.add(campeonatoJogadoresCaros);
        }

        return campeonatoJogadoresCarosDTOs;

    }
}
