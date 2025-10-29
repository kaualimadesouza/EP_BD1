package org.example.goalytics.controller;

import org.example.goalytics.model.Substituicao;
import org.example.goalytics.service.SubstituicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/substituicao")
@CrossOrigin(origins = "*")
public class SubstituicaoController {

    private final SubstituicaoService substituicaoService;

    public SubstituicaoController(SubstituicaoService substituicaoService) {
        this.substituicaoService = substituicaoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Substituicao> listarSubstituicoes() {
        return substituicaoService.listarSubstituicoes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Substituicao> obterSubstituicaoPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Substituicao substituicao = substituicaoService.obterSubstituicaoPorNumEvento(numEvento);
            return ResponseEntity.ok(substituicao);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarSubstituicao(@PathVariable Integer numEvento) {
        try {
            substituicaoService.excluirSubstituicao(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir substituicao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = substituicaoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirSubstituicao(@RequestBody Substituicao substituicao) {
        try {
            substituicaoService.inserirSubstituicao(substituicao);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir substituicao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarSubstituicao(@RequestBody Substituicao substituicao, @PathVariable Integer numEvento) {
        try {
            substituicaoService.atualizarSubstituicao(substituicao, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar substituicao: " + e.getMessage());
        }
    }
}
