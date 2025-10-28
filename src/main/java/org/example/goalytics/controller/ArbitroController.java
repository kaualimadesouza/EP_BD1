package org.example.goalytics.controller;

import org.example.goalytics.model.Arbitro;
import org.example.goalytics.service.ArbitroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arbitro")
public class ArbitroController {
    private final ArbitroService arbitroService;

    public ArbitroController(ArbitroService arbitroService) {
        this.arbitroService = arbitroService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Arbitro> listarArbitros() {
        return arbitroService.listarArbitros();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Arbitro> obterArbitroPorId(@PathVariable Integer id) {
        try {
            Arbitro arbitro = arbitroService.obterArbitroPorId(id);
            return ResponseEntity.ok(arbitro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarArbitro(@PathVariable Integer id) {
        try {
            arbitroService.excluirArbitro(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir árbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirArbitro(@RequestBody Arbitro arbitro) {
        try {
            arbitroService.inserirArbitro(arbitro);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir árbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarArbitro(@RequestBody Arbitro arbitro, @PathVariable Integer id) {
        try {
            arbitroService.atualizarArbitro(arbitro, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar árbitro: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = arbitroService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(java.util.Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }
}
