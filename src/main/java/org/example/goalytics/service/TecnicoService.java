package org.example.goalytics.service;

import org.example.goalytics.model.Tecnico;
import org.example.goalytics.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public List<Tecnico> listarTecnicos() {
        return tecnicoRepository.listarTodos();
    }

    public void inserirTecnico(Tecnico tecnico) {
        tecnicoRepository.inserir(tecnico);
    }

    public void atualizarTecnico(Tecnico tecnico, Integer id) {
        if (tecnicoRepository.existePorId(id)) {
            tecnicoRepository.atualizar(tecnico, id);
        } else {
            throw new RuntimeException("Técnico com id " + id + " não encontrado.");
        }
    }

    public void excluirTecnico(Integer id) {
        if (!tecnicoRepository.existePorId(id)) {
            throw new RuntimeException("Técnico com id " + id + " não encontrado.");
        }
        tecnicoRepository.excluirTecnico(id);
    }

    public Tecnico obterTecnicoPorId(Integer id) {
        return tecnicoRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return tecnicoRepository.listarNomeColunas();
    }
}

