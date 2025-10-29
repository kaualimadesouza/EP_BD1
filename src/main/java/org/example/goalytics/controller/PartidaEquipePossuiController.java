package org.example.goalytics.controller;

import org.example.goalytics.model.PartidaEquipePossui;
import org.example.goalytics.service.PartidaEquipePossuiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/partida_equipe_possui")
public class PartidaEquipePossuiController {
    private final PartidaEquipePossuiService partidaEquipePossuiService;

    public PartidaEquipePossuiController(PartidaEquipePossuiService partidaEquipePossuiService) {
        this.partidaEquipePossuiService = partidaEquipePossuiService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<PartidaEquipePossui> listarTodos() {
        return partidaEquipePossuiService.listarTodos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<PartidaEquipePossui> obterPorId(@PathVariable Integer id) {
        try {
            PartidaEquipePossui pep = partidaEquipePossuiService.obterPorId(id);
            return ResponseEntity.ok(pep);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody PartidaEquipePossui partidaEquipePossui) {
        try {
            partidaEquipePossuiService.inserir(partidaEquipePossui);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir partida_equipe_possui: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@RequestBody PartidaEquipePossui partidaEquipePossui, @PathVariable Integer id) {
        try {
            partidaEquipePossuiService.atualizar(partidaEquipePossui, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar partida_equipe_possui: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        try {
            partidaEquipePossuiService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao excluir partida_equipe_possui: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = partidaEquipePossuiService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }
}
