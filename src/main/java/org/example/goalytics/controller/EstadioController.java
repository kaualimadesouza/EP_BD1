package org.example.goalytics.controller;

import org.example.goalytics.model.Estadio;
import org.example.goalytics.service.EstadioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/estadio")
public class EstadioController {

    private final EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Estadio> listarEstadios() {
        return estadioService.listarEstadios();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEstadio(@PathVariable Integer id) {
        try {
            estadioService.excluirEstadio(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir estádio: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping ("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = estadioService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

}
