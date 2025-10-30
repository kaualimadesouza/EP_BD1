package org.example.goalytics.service;

import org.example.goalytics.Records.PartidaDetalhesDTO;
import org.example.goalytics.model.Partida;
import org.example.goalytics.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public List<Partida> listarPartidas() {
        return partidaRepository.listarTodos();
    }

    public void inserirPartida(Partida partida) {
        partidaRepository.inserir(partida);
    }

    public void atualizarPartida(Partida partida, Integer id) {
        if (partidaRepository.existePorId(id)) {
            partidaRepository.atualizar(partida, id);
        } else {
            throw new RuntimeException("Partida com id " + id + " não encontrada.");
        }
    }

    public void excluirPartida(Integer id) {
        if (!partidaRepository.existePorId(id)) {
            throw new RuntimeException("Partida com id " + id + " não encontrada.");
        }
        partidaRepository.excluirPartida(id);
    }

    public Partida obterPartidaPorId(Integer id) {
        return partidaRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return partidaRepository.listarNomeColunas();
    }


    public List<PartidaDetalhesDTO> obterUltimaPartidaDetalhes() {
        return partidaRepository.obterUltimaPartidaDetalhes();
    }
}

