package org.example.goalytics.controller;

import org.example.goalytics.model.Defesa;
import org.example.goalytics.service.DefesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/defesa")
@CrossOrigin(origins = "*")
public class DefesaController {

    private final DefesaService defesaService;

    public DefesaController(DefesaService defesaService) {
        this.defesaService = defesaService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Defesa> listarDefesas() {
        return defesaService.listarDefesas();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Defesa> obterDefesaPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Defesa defesa = defesaService.obterDefesaPorNumEvento(numEvento);
            return ResponseEntity.ok(defesa);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarDefesa(@PathVariable Integer numEvento) {
        try {
            defesaService.excluirDefesa(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir defesa: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = defesaService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirDefesa(@RequestBody Defesa defesa) {
        try {
            defesaService.inserirDefesa(defesa);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir defesa: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarDefesa(@RequestBody Defesa defesa, @PathVariable Integer numEvento) {
        try {
            defesaService.atualizarDefesa(defesa, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar defesa: " + e.getMessage());
        }
    }
}
