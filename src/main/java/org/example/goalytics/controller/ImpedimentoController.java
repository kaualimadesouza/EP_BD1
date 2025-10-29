package org.example.goalytics.controller;

import org.example.goalytics.model.Impedimento;
import org.example.goalytics.service.ImpedimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/impedimento")
@CrossOrigin(origins = "*")
public class ImpedimentoController {

    private final ImpedimentoService impedimentoService;

    public ImpedimentoController(ImpedimentoService impedimentoService) {
        this.impedimentoService = impedimentoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Impedimento> listarImpedimentos() {
        return impedimentoService.listarImpedimentos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Impedimento> obterImpedimentoPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Impedimento impedimento = impedimentoService.obterImpedimentoPorNumEvento(numEvento);
            return ResponseEntity.ok(impedimento);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarImpedimento(@PathVariable Integer numEvento) {
        try {
            impedimentoService.excluirImpedimento(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir impedimento: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = impedimentoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirImpedimento(@RequestBody Impedimento impedimento) {
        try {
            impedimentoService.inserirImpedimento(impedimento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir impedimento: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarImpedimento(@RequestBody Impedimento impedimento, @PathVariable Integer numEvento) {
        try {
            impedimentoService.atualizarImpedimento(impedimento, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar impedimento: " + e.getMessage());
        }
    }
}
