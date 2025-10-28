package org.example.goalytics.service;

import org.example.goalytics.model.Arbitro;
import org.example.goalytics.repository.ArbitroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArbitroService {
    private final ArbitroRepository arbitroRepository;

    public ArbitroService(ArbitroRepository arbitroRepository) {
        this.arbitroRepository = arbitroRepository;
    }

    public List<Arbitro> listarArbitros() {
        return arbitroRepository.listarTodos();
    }

    public void inserirArbitro(Arbitro arbitro) {
        arbitroRepository.inserir(arbitro);
    }

    public void atualizarArbitro(Arbitro arbitro, Integer id) {
        if (arbitroRepository.existePorId(id)) {
            arbitroRepository.atualizar(arbitro, id);
        } else {
            throw new RuntimeException("Árbitro com id " + id + " não encontrado.");
        }
    }

    public void excluirArbitro(Integer id) {
        if (!arbitroRepository.existePorId(id)) {
            throw new RuntimeException("Árbitro com id " + id + " não encontrado.");
        }
        arbitroRepository.excluirArbitro(id);
    }

    public Arbitro obterArbitroPorId(Integer id) {
        return arbitroRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return arbitroRepository.listarNomeColunas();
    }
}
