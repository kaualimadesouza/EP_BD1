package org.example.goalytics.controller;

import org.example.goalytics.model.Cartao;
import org.example.goalytics.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cartao")
@CrossOrigin(origins = "*")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Cartao> listarCartoes() {
        return cartaoService.listarCartoes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Cartao> obterCartaoPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Cartao cartao = cartaoService.obterCartaoPorNumEvento(numEvento);
            return ResponseEntity.ok(cartao);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarCartao(@PathVariable Integer numEvento) {
        try {
            cartaoService.excluirCartao(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir cartao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = cartaoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirCartao(@RequestBody Cartao cartao) {
        try {
            cartaoService.inserirCartao(cartao);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir cartao: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarCartao(@RequestBody Cartao cartao, @PathVariable Integer numEvento) {
        try {
            cartaoService.atualizarCartao(cartao, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar cartao: " + e.getMessage());
        }
    }
}
