package org.example.goalytics.controller;

import org.example.goalytics.model.JogadorEquipe;
import org.example.goalytics.service.JogadorEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/jogador_equipe")
public class JogadorEquipeController {
    private final JogadorEquipeService jogadorEquipeService;

    public JogadorEquipeController(JogadorEquipeService jogadorEquipeService) {
        this.jogadorEquipeService = jogadorEquipeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<JogadorEquipe> listarTodos() {
        return jogadorEquipeService.listarTodos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{idEquipe}/{idJogador}")
    public ResponseEntity<JogadorEquipe> obterPorId(@PathVariable Integer idEquipe, @PathVariable Integer idJogador) {
        try {
            JogadorEquipe je = jogadorEquipeService.obterPorId(idEquipe, idJogador);
            return ResponseEntity.ok(je);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<JogadorEquipe> obterPorId(@PathVariable Integer id) {
        try {
            JogadorEquipe je = jogadorEquipeService.obterPorId(id);
            return ResponseEntity.ok(je);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        try {
            jogadorEquipeService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao excluir jogador_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = jogadorEquipeService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserir(@RequestBody JogadorEquipe jogadorEquipe) {
        try {
            jogadorEquipeService.inserir(jogadorEquipe);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir jogador_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping()
    public ResponseEntity<String> atualizar(@RequestBody JogadorEquipe jogadorEquipe) {
        try {
            jogadorEquipeService.atualizar(jogadorEquipe);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar jogador_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarJogadorEquipe(@RequestBody JogadorEquipe jogadorEquipe, @PathVariable Integer id) {
        try {
            jogadorEquipeService.atualizar(jogadorEquipe, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar jogador_equipe: " + e.getMessage());
        }
    }
}
