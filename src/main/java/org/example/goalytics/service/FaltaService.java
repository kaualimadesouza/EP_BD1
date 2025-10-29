package org.example.goalytics.service;

import org.example.goalytics.model.Falta;
import org.example.goalytics.repository.FaltaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaltaService {

    private final FaltaRepository faltaRepository;

    public FaltaService(FaltaRepository faltaRepository) {
        this.faltaRepository = faltaRepository;
    }

    public List<Falta> listarFaltas() {
        return faltaRepository.listarTodos();
    }

    public void excluirFalta(Integer numEvento) {
        if (!faltaRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Falta com num_evento " + numEvento + " não encontrado.");
        }
        faltaRepository.excluirFalta(numEvento);
    }

    public List<String> obterColunas() {
        return faltaRepository.listarNomeColunas();
    }

    public void inserirFalta(Falta falta) {
        faltaRepository.inserir(falta);
    }

    public void atualizarFalta(Falta falta, Integer numEvento) {
        if (faltaRepository.existePorNumEvento(numEvento)) {
            faltaRepository.atualizar(falta, numEvento);
        } else {
            throw new RuntimeException("Falta com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Falta obterFaltaPorNumEvento(Integer numEvento) {
        return faltaRepository.obterPorNumEvento(numEvento);
    }
}
