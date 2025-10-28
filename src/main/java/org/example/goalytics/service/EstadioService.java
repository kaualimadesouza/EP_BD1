package org.example.goalytics.service;

import org.example.goalytics.model.Estadio;
import org.example.goalytics.repository.EstadioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadioService {

    private final EstadioRepository estadioRepository;

    public EstadioService(EstadioRepository estadioRepository) {
        this.estadioRepository = estadioRepository;
    }

    public List<Estadio> listarEstadios() {
        return estadioRepository.listarTodos();
    }

    public void excluirEstadio(Integer id) {
        if (!estadioRepository.existePorId(id)) {
            throw new RuntimeException("Estádio com id " + id + " não encontrado.");
        }
        estadioRepository.excluirEstadio(id);
    }

    public List<String> obterColunas() {
        return estadioRepository.listarNomeColunas();
    }

    public void inserirEstadio(Estadio estadio) {
        estadioRepository.inserir(estadio);
    }

    public void atualizarEstadio(Estadio estadio, Integer id) {
        if (estadioRepository.existePorId(id)) {
            estadioRepository.atualizar(estadio, id);
        } else {
            throw new RuntimeException("Estádio com id " + id + " não encontrado.");
        }
    }

    public Estadio obterEstadioPorId(Integer id) {
        return estadioRepository.obterPorId(id);
    }
}
