package org.example.goalytics.service;

import org.example.goalytics.model.CampeonatoEquipe;
import org.example.goalytics.repository.CampeonatoEquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoEquipeService {

    private final CampeonatoEquipeRepository campeonatoEquipeRepository;

    public CampeonatoEquipeService(CampeonatoEquipeRepository campeonatoEquipeRepository) {
        this.campeonatoEquipeRepository = campeonatoEquipeRepository;
    }

    public List<CampeonatoEquipe> listarCampeonatoEquipes() {
        return campeonatoEquipeRepository.listarTodos();
    }

    public void excluirCampeonatoEquipe(Integer id) {
        if (!campeonatoEquipeRepository.existePorId(id)) {
            throw new RuntimeException("CampeonatoEquipe com id " + id + " não encontrado.");
        }
        campeonatoEquipeRepository.excluirCampeonatoEquipe(id);
    }

    public List<String> obterColunas() {
        return campeonatoEquipeRepository.listarNomeColunas();
    }

    public void inserirCampeonatoEquipe(CampeonatoEquipe campeonatoEquipe) {
        campeonatoEquipeRepository.inserir(campeonatoEquipe);
    }

    public void atualizarCampeonatoEquipe(CampeonatoEquipe campeonatoEquipe, Integer id) {
        if (campeonatoEquipeRepository.existePorId(id)) {
            campeonatoEquipeRepository.atualizar(campeonatoEquipe, id);
        } else {
            throw new RuntimeException("CampeonatoEquipe com id " + id + " não encontrado.");
        }
    }

    public CampeonatoEquipe obterCampeonatoEquipePorId(Integer id) {
        return campeonatoEquipeRepository.obterPorId(id);
    }
}
