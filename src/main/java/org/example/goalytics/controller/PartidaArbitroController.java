package org.example.goalytics.controller;

import org.example.goalytics.model.PartidaArbitro;
import org.example.goalytics.service.PartidaArbitroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/partida_arbitro")
public class PartidaArbitroController {
    private final PartidaArbitroService partidaArbitroService;

    public PartidaArbitroController(PartidaArbitroService partidaArbitroService) {
        this.partidaArbitroService = partidaArbitroService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<PartidaArbitro> listarTodos() {
        return partidaArbitroService.listarTodos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<PartidaArbitro> obterPorId(@PathVariable Integer id) {
        try {
            PartidaArbitro pa = partidaArbitroService.obterPorId(id);
            return ResponseEntity.ok(pa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody PartidaArbitro partidaArbitro) {
        try {
            partidaArbitroService.inserir(partidaArbitro);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir partida_arbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@RequestBody PartidaArbitro partidaArbitro, @PathVariable Integer id) {
        try {
            partidaArbitroService.atualizar(partidaArbitro, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar partida_arbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        try {
            partidaArbitroService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao excluir partida_arbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = partidaArbitroService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }
}

