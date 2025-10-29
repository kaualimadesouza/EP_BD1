package org.example.goalytics.service;

import org.example.goalytics.model.Substituicao;
import org.example.goalytics.repository.SubstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubstituicaoService {

    private final SubstituicaoRepository substituicaoRepository;

    public SubstituicaoService(SubstituicaoRepository substituicaoRepository) {
        this.substituicaoRepository = substituicaoRepository;
    }

    public List<Substituicao> listarSubstituicoes() {
        return substituicaoRepository.listarTodos();
    }

    public void excluirSubstituicao(Integer numEvento) {
        if (!substituicaoRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Substituicao com num_evento " + numEvento + " não encontrado.");
        }
        substituicaoRepository.excluirSubstituicao(numEvento);
    }

    public List<String> obterColunas() {
        return substituicaoRepository.listarNomeColunas();
    }

    public void inserirSubstituicao(Substituicao substituicao) {
        substituicaoRepository.inserir(substituicao);
    }

    public void atualizarSubstituicao(Substituicao substituicao, Integer numEvento) {
        if (substituicaoRepository.existePorNumEvento(numEvento)) {
            substituicaoRepository.atualizar(substituicao, numEvento);
        } else {
            throw new RuntimeException("Substituicao com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Substituicao obterSubstituicaoPorNumEvento(Integer numEvento) {
        return substituicaoRepository.obterPorNumEvento(numEvento);
    }
}
