package org.example.goalytics.service;

import org.example.goalytics.model.TecnicoEquipe;
import org.example.goalytics.repository.TecnicoEquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoEquipeService {

    private final TecnicoEquipeRepository tecnicoEquipeRepository;

    public TecnicoEquipeService(TecnicoEquipeRepository tecnicoEquipeRepository) {
        this.tecnicoEquipeRepository = tecnicoEquipeRepository;
    }

    public List<TecnicoEquipe> listarTecnicoEquipes() {
        return tecnicoEquipeRepository.listarTodos();
    }

    public void excluirTecnicoEquipe(Integer id) {
        if (!tecnicoEquipeRepository.existePorId(id)) {
            throw new RuntimeException("TecnicoEquipe com id " + id + " não encontrado.");
        }
        tecnicoEquipeRepository.excluirTecnicoEquipe(id);
    }

    public List<String> obterColunas() {
        return tecnicoEquipeRepository.listarNomeColunas();
    }

    public void inserirTecnicoEquipe(TecnicoEquipe tecnicoEquipe) {
        tecnicoEquipeRepository.inserir(tecnicoEquipe);
    }

    public void atualizarTecnicoEquipe(TecnicoEquipe tecnicoEquipe, Integer id) {
        if (tecnicoEquipeRepository.existePorId(id)) {
            tecnicoEquipeRepository.atualizar(tecnicoEquipe, id);
        } else {
            throw new RuntimeException("TecnicoEquipe com id " + id + " não encontrado.");
        }
    }

    public TecnicoEquipe obterTecnicoEquipePorId(Integer id) {
        return tecnicoEquipeRepository.obterPorId(id);
    }
}
