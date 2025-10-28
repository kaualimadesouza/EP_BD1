package org.example.goalytics.controller;

import org.example.goalytics.model.Tecnico;
import org.example.goalytics.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {
    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Tecnico> listarTecnicos() {
        return tecnicoService.listarTecnicos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> obterTecnicoPorId(@PathVariable Integer id) {
        try {
            Tecnico tecnico = tecnicoService.obterTecnicoPorId(id);
            return ResponseEntity.ok(tecnico);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTecnico(@PathVariable Integer id) {
        try {
            tecnicoService.excluirTecnico(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir técnico: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = tecnicoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirTecnico(@RequestBody Tecnico tecnico) {
        try {
            tecnicoService.inserirTecnico(tecnico);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir técnico: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarTecnico(@RequestBody Tecnico tecnico, @PathVariable Integer id) {
        try {
            tecnicoService.atualizarTecnico(tecnico, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar técnico: " + e.getMessage());
        }
    }
}
