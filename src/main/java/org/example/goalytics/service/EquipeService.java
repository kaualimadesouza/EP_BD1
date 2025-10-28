package org.example.goalytics.service;

import org.example.goalytics.model.Equipe;
import org.example.goalytics.repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<Equipe> listarEquipes() {
        return equipeRepository.listarTodos();
    }

    public void inserirEquipe(Equipe equipe) {
        equipeRepository.inserir(equipe);
    }

    public void atualizarEquipe(Equipe equipe, Integer id) {
        if (equipeRepository.existePorId(id)) {
            equipeRepository.atualizar(equipe, id);
        } else {
            throw new RuntimeException("Equipe com id " + id + " não encontrada.");
        }
    }

    public void excluirEquipe(Integer id) {
        if (!equipeRepository.existePorId(id)) {
            throw new RuntimeException("Equipe com id " + id + " não encontrada.");
        }
        equipeRepository.excluirEquipe(id);
    }

    public Equipe obterEquipePorId(Integer id) {
        return equipeRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return equipeRepository.listarNomeColunas();
    }
}

