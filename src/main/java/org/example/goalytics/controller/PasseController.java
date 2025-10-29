package org.example.goalytics.controller;

import org.example.goalytics.model.Passe;
import org.example.goalytics.service.PasseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/passe")
@CrossOrigin(origins = "*")
public class PasseController {

    private final PasseService passeService;

    public PasseController(PasseService passeService) {
        this.passeService = passeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Passe> listarPasses() {
        return passeService.listarPasses();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Passe> obterPassePorNumEvento(@PathVariable Integer numEvento) {
        try {
            Passe passe = passeService.obterPassePorNumEvento(numEvento);
            return ResponseEntity.ok(passe);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarPasse(@PathVariable Integer numEvento) {
        try {
            passeService.excluirPasse(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir passe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = passeService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirPasse(@RequestBody Passe passe) {
        try {
            passeService.inserirPasse(passe);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir passe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarPasse(@RequestBody Passe passe, @PathVariable Integer numEvento) {
        try {
            passeService.atualizarPasse(passe, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar passe: " + e.getMessage());
        }
    }
}
