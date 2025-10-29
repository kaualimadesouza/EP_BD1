package org.example.goalytics.controller;

import org.example.goalytics.model.Finalizacao;
import org.example.goalytics.service.FinalizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/finalizacao")
@CrossOrigin(origins = "*")
public class FinalizacaoController {

    private final FinalizacaoService finalizacaoService;

    public FinalizacaoController(FinalizacaoService finalizacaoService) {
        this.finalizacaoService = finalizacaoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Finalizacao> listarFinalizacoes() {
        return finalizacaoService.listarFinalizacoes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Finalizacao> obterFinalizacaoPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Finalizacao finalizacao = finalizacaoService.obterFinalizacaoPorNumEvento(numEvento);
            return ResponseEntity.ok(finalizacao);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarFinalizacao(@PathVariable Integer numEvento) {
        try {
            finalizacaoService.excluirFinalizacao(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir finalizacao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = finalizacaoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirFinalizacao(@RequestBody Finalizacao finalizacao) {
        try {
            finalizacaoService.inserirFinalizacao(finalizacao);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir finalizacao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarFinalizacao(@RequestBody Finalizacao finalizacao, @PathVariable Integer numEvento) {
        try {
            finalizacaoService.atualizarFinalizacao(finalizacao, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar finalizacao: " + e.getMessage());
        }
    }
}
