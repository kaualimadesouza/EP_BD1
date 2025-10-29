package org.example.goalytics.service;

import org.example.goalytics.model.Impedimento;
import org.example.goalytics.repository.ImpedimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpedimentoService {

    private final ImpedimentoRepository impedimentoRepository;

    public ImpedimentoService(ImpedimentoRepository impedimentoRepository) {
        this.impedimentoRepository = impedimentoRepository;
    }

    public List<Impedimento> listarImpedimentos() {
        return impedimentoRepository.listarTodos();
    }

    public void excluirImpedimento(Integer numEvento) {
        if (!impedimentoRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Impedimento com num_evento " + numEvento + " não encontrado.");
        }
        impedimentoRepository.excluirImpedimento(numEvento);
    }

    public List<String> obterColunas() {
        return impedimentoRepository.listarNomeColunas();
    }

    public void inserirImpedimento(Impedimento impedimento) {
        impedimentoRepository.inserir(impedimento);
    }

    public void atualizarImpedimento(Impedimento impedimento, Integer numEvento) {
        if (impedimentoRepository.existePorNumEvento(numEvento)) {
            impedimentoRepository.atualizar(impedimento, numEvento);
        } else {
            throw new RuntimeException("Impedimento com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Impedimento obterImpedimentoPorNumEvento(Integer numEvento) {
        return impedimentoRepository.obterPorNumEvento(numEvento);
    }
}
