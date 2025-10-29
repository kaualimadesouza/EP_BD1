package org.example.goalytics.controller;

import org.example.goalytics.model.TecnicoEquipe;
import org.example.goalytics.service.TecnicoEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tecnico-equipe")
@CrossOrigin(origins = "*")
public class TecnicoEquipeController {

    private final TecnicoEquipeService tecnicoEquipeService;

    public TecnicoEquipeController(TecnicoEquipeService tecnicoEquipeService) {
        this.tecnicoEquipeService = tecnicoEquipeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<TecnicoEquipe> listarTecnicoEquipes() {
        return tecnicoEquipeService.listarTecnicoEquipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoEquipe> obterTecnicoEquipePorId(@PathVariable Integer id) {
        try {
            TecnicoEquipe tecnicoEquipe = tecnicoEquipeService.obterTecnicoEquipePorId(id);
            return ResponseEntity.ok(tecnicoEquipe);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTecnicoEquipe(@PathVariable Integer id) {
        try {
            tecnicoEquipeService.excluirTecnicoEquipe(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir tecnico_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = tecnicoEquipeService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirTecnicoEquipe(@RequestBody TecnicoEquipe tecnicoEquipe) {
        try {
            tecnicoEquipeService.inserirTecnicoEquipe(tecnicoEquipe);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir tecnico_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarTecnicoEquipe(@RequestBody TecnicoEquipe tecnicoEquipe, @PathVariable Integer id) {
        try {
            tecnicoEquipeService.atualizarTecnicoEquipe(tecnicoEquipe, id);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar tecnico_equipe: " + e.getMessage());
        }
    }
}
