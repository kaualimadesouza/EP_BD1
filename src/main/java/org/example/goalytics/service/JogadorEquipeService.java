package org.example.goalytics.service;

import org.example.goalytics.model.JogadorEquipe;
import org.example.goalytics.repository.JogadorEquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorEquipeService {
    private final JogadorEquipeRepository jogadorEquipeRepository;

    public JogadorEquipeService(JogadorEquipeRepository jogadorEquipeRepository) {
        this.jogadorEquipeRepository = jogadorEquipeRepository;
    }

    public List<JogadorEquipe> listarTodos() {
        return jogadorEquipeRepository.listarTodos();
    }

    public void inserir(JogadorEquipe jogadorEquipe) {
        jogadorEquipeRepository.inserir(jogadorEquipe);
    }

    public void atualizar(JogadorEquipe jogadorEquipe) {
        jogadorEquipeRepository.atualizar(jogadorEquipe);
    }

    public void excluir(Integer idEquipe, Integer idJogador) {
        jogadorEquipeRepository.excluir(idEquipe, idJogador);
    }

    public JogadorEquipe obterPorId(Integer idEquipe, Integer idJogador) {
        return jogadorEquipeRepository.obterPorId(idEquipe, idJogador);
    }

    public JogadorEquipe obterPorId(Integer id) {
        return jogadorEquipeRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return jogadorEquipeRepository.listarNomeColunas();
    }
}
