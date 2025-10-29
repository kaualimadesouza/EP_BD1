package org.example.goalytics.service;

import org.example.goalytics.model.Escanteio;
import org.example.goalytics.repository.EscanteioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscanteioService {

    private final EscanteioRepository escanteioRepository;

    public EscanteioService(EscanteioRepository escanteioRepository) {
        this.escanteioRepository = escanteioRepository;
    }

    public List<Escanteio> listarEscanteios() {
        return escanteioRepository.listarTodos();
    }

    public void excluirEscanteio(Integer numEvento) {
        if (!escanteioRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Escanteio com num_evento " + numEvento + " não encontrado.");
        }
        escanteioRepository.excluirEscanteio(numEvento);
    }

    public List<String> obterColunas() {
        return escanteioRepository.listarNomeColunas();
    }

    public void inserirEscanteio(Escanteio escanteio) {
        escanteioRepository.inserir(escanteio);
    }

    public void atualizarEscanteio(Escanteio escanteio, Integer numEvento) {
        if (escanteioRepository.existePorNumEvento(numEvento)) {
            escanteioRepository.atualizar(escanteio, numEvento);
        } else {
            throw new RuntimeException("Escanteio com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Escanteio obterEscanteioPorNumEvento(Integer numEvento) {
        return escanteioRepository.obterPorNumEvento(numEvento);
    }
}
