package org.example.goalytics.service;

import org.example.goalytics.model.Jogador;
import org.example.goalytics.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.listarTodos();
    }

    public void inserirJogador(Jogador jogador) {
        jogadorRepository.inserir(jogador);
    }

    public void atualizarJogador(Jogador jogador, Integer id) {
        if (jogadorRepository.existePorId(id)) {
            jogadorRepository.atualizar(jogador, id);
        } else {
            throw new RuntimeException("Jogador com id " + id + " não encontrado.");
        }
    }

    public void excluirJogador(Integer id) {
        if (!jogadorRepository.existePorId(id)) {
            throw new RuntimeException("Jogador com id " + id + " não encontrado.");
        }
        jogadorRepository.excluirJogador(id);
    }

    public Jogador obterJogadorPorId(Integer id) {
        return jogadorRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return jogadorRepository.listarNomeColunas();
    }
}

