package org.example.goalytics.service;

import org.example.goalytics.Records.CampeonatoJogosDTO;
import org.example.goalytics.model.Campeonato;
import org.example.goalytics.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {
    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository) {
        this.campeonatoRepository = campeonatoRepository;
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

    public List<CampeonatoJogosDTO> listarCampeonatosComJogosRecentes() {
        

        // 1. Buscar todos os campeonatos
        List<Campeonato> campeonatos = campeonatoRepository.listarTodos();

        // 2. Para cada campeonato, buscar os jogos recentes e montar o DTO
        for (Campeonato campeonato : campeonatos) {
            List<CampeonatoJogosDTO> jogosRecentes = campeonatoRepository.listarJogosRecentesPorCampeonato(campeonato.getId());
            for (CampeonatoJogosDTO dto : jogosRecentes) {
                return jogosRecentes;
            }
        }
    }
}

