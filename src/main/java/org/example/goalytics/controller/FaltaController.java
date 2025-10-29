package org.example.goalytics.controller;

import org.example.goalytics.model.Falta;
import org.example.goalytics.service.FaltaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/falta")
@CrossOrigin(origins = "*")
public class FaltaController {

    private final FaltaService faltaService;

    public FaltaController(FaltaService faltaService) {
        this.faltaService = faltaService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Falta> listarFaltas() {
        return faltaService.listarFaltas();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Falta> obterFaltaPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Falta falta = faltaService.obterFaltaPorNumEvento(numEvento);
            return ResponseEntity.ok(falta);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarFalta(@PathVariable Integer numEvento) {
        try {
            faltaService.excluirFalta(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir falta: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = faltaService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirFalta(@RequestBody Falta falta) {
        try {
            faltaService.inserirFalta(falta);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir falta: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarFalta(@RequestBody Falta falta, @PathVariable Integer numEvento) {
        try {
            faltaService.atualizarFalta(falta, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar falta: " + e.getMessage());
        }
    }
}
