package org.example.goalytics.service;

import org.example.goalytics.model.Passe;
import org.example.goalytics.repository.PasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasseService {

    private final PasseRepository passeRepository;

    public PasseService(PasseRepository passeRepository) {
        this.passeRepository = passeRepository;
    }

    public List<Passe> listarPasses() {
        return passeRepository.listarTodos();
    }

    public void excluirPasse(Integer numEvento) {
        if (!passeRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Passe com num_evento " + numEvento + " não encontrado.");
        }
        passeRepository.excluirPasse(numEvento);
    }

    public List<String> obterColunas() {
        return passeRepository.listarNomeColunas();
    }

    public void inserirPasse(Passe passe) {
        passeRepository.inserir(passe);
    }

    public void atualizarPasse(Passe passe, Integer numEvento) {
        if (passeRepository.existePorNumEvento(numEvento)) {
            passeRepository.atualizar(passe, numEvento);
        } else {
            throw new RuntimeException("Passe com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Passe obterPassePorNumEvento(Integer numEvento) {
        return passeRepository.obterPorNumEvento(numEvento);
    }
}
