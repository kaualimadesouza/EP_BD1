package org.example.goalytics.service;

import org.example.goalytics.model.Finalizacao;
import org.example.goalytics.repository.FinalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalizacaoService {

    private final FinalizacaoRepository finalizacaoRepository;

    public FinalizacaoService(FinalizacaoRepository finalizacaoRepository) {
        this.finalizacaoRepository = finalizacaoRepository;
    }

    public List<Finalizacao> listarFinalizacoes() {
        return finalizacaoRepository.listarTodos();
    }

    public void excluirFinalizacao(Integer numEvento) {
        if (!finalizacaoRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Finalizacao com num_evento " + numEvento + " não encontrado.");
        }
        finalizacaoRepository.excluirFinalizacao(numEvento);
    }

    public List<String> obterColunas() {
        return finalizacaoRepository.listarNomeColunas();
    }

    public void inserirFinalizacao(Finalizacao finalizacao) {
        finalizacaoRepository.inserir(finalizacao);
    }

    public void atualizarFinalizacao(Finalizacao finalizacao, Integer numEvento) {
        if (finalizacaoRepository.existePorNumEvento(numEvento)) {
            finalizacaoRepository.atualizar(finalizacao, numEvento);
        } else {
            throw new RuntimeException("Finalizacao com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Finalizacao obterFinalizacaoPorNumEvento(Integer numEvento) {
        return finalizacaoRepository.obterPorNumEvento(numEvento);
    }
}
