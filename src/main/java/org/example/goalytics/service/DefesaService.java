package org.example.goalytics.service;

import org.example.goalytics.model.Defesa;
import org.example.goalytics.repository.DefesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefesaService {

    private final DefesaRepository defesaRepository;

    public DefesaService(DefesaRepository defesaRepository) {
        this.defesaRepository = defesaRepository;
    }

    public List<Defesa> listarDefesas() {
        return defesaRepository.listarTodos();
    }

    public void excluirDefesa(Integer numEvento) {
        if (!defesaRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Defesa com num_evento " + numEvento + " não encontrado.");
        }
        defesaRepository.excluirDefesa(numEvento);
    }

    public List<String> obterColunas() {
        return defesaRepository.listarNomeColunas();
    }

    public void inserirDefesa(Defesa defesa) {
        defesaRepository.inserir(defesa);
    }

    public void atualizarDefesa(Defesa defesa, Integer numEvento) {
        if (defesaRepository.existePorNumEvento(numEvento)) {
            defesaRepository.atualizar(defesa, numEvento);
        } else {
            throw new RuntimeException("Defesa com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Defesa obterDefesaPorNumEvento(Integer numEvento) {
        return defesaRepository.obterPorNumEvento(numEvento);
    }
}
