package org.example.goalytics.service;

import org.example.goalytics.model.Drible;
import org.example.goalytics.repository.DribleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DribleService {

    private final DribleRepository dribleRepository;

    public DribleService(DribleRepository dribleRepository) {
        this.dribleRepository = dribleRepository;
    }

    public List<Drible> listarDribles() {
        return dribleRepository.listarTodos();
    }

    public void excluirDrible(Integer numEvento) {
        if (!dribleRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Drible com num_evento " + numEvento + " não encontrado.");
        }
        dribleRepository.excluirDrible(numEvento);
    }

    public List<String> obterColunas() {
        return dribleRepository.listarNomeColunas();
    }

    public void inserirDrible(Drible drible) {
        dribleRepository.inserir(drible);
    }

    public void atualizarDrible(Drible drible, Integer numEvento) {
        if (dribleRepository.existePorNumEvento(numEvento)) {
            dribleRepository.atualizar(drible, numEvento);
        } else {
            throw new RuntimeException("Drible com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Drible obterDriblePorNumEvento(Integer numEvento) {
        return dribleRepository.obterPorNumEvento(numEvento);
    }
}
