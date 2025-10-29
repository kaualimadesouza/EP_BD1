package org.example.goalytics.service;

import org.example.goalytics.model.PartidaArbitro;
import org.example.goalytics.repository.PartidaArbitroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaArbitroService {
    private final PartidaArbitroRepository partidaArbitroRepository;

    public PartidaArbitroService(PartidaArbitroRepository partidaArbitroRepository) {
        this.partidaArbitroRepository = partidaArbitroRepository;
    }

    public List<PartidaArbitro> listarTodos() {
        return partidaArbitroRepository.listarTodos();
    }

    public void inserir(PartidaArbitro partidaArbitro) {
        partidaArbitroRepository.inserir(partidaArbitro);
    }

    public void atualizar(PartidaArbitro partidaArbitro, Integer id) {
        partidaArbitroRepository.atualizar(partidaArbitro, id);
    }

    public void excluir(Integer id) {
        partidaArbitroRepository.excluir(id);
    }

    public PartidaArbitro obterPorId(Integer id) {
        return partidaArbitroRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return partidaArbitroRepository.listarNomeColunas();
    }
}

