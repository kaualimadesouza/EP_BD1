package org.example.goalytics.service;

import org.example.goalytics.model.PartidaEquipePossui;
import org.example.goalytics.repository.PartidaEquipePossuiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaEquipePossuiService {
    private final PartidaEquipePossuiRepository partidaEquipePossuiRepository;

    public PartidaEquipePossuiService(PartidaEquipePossuiRepository partidaEquipePossuiRepository) {
        this.partidaEquipePossuiRepository = partidaEquipePossuiRepository;
    }

    public List<PartidaEquipePossui> listarTodos() {
        return partidaEquipePossuiRepository.listarTodos();
    }

    public void inserir(PartidaEquipePossui partidaEquipePossui) {
        partidaEquipePossuiRepository.inserir(partidaEquipePossui);
    }

    public void atualizar(PartidaEquipePossui partidaEquipePossui, Integer id) {
        partidaEquipePossuiRepository.atualizar(partidaEquipePossui, id);
    }

    public void excluir(Integer id) {
        partidaEquipePossuiRepository.excluir(id);
    }

    public PartidaEquipePossui obterPorId(Integer id) {
        return partidaEquipePossuiRepository.obterPorId(id);
    }

    public List<String> obterColunas() {
        return partidaEquipePossuiRepository.listarNomeColunas();
    }
}
