package org.example.goalytics.controller;

import org.example.goalytics.model.Escanteio;
import org.example.goalytics.service.EscanteioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/escanteio")
@CrossOrigin(origins = "*")
public class EscanteioController {

    private final EscanteioService escanteioService;

    public EscanteioController(EscanteioService escanteioService) {
        this.escanteioService = escanteioService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Escanteio> listarEscanteios() {
        return escanteioService.listarEscanteios();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Escanteio> obterEscanteioPorNumEvento(@PathVariable Integer numEvento) {
        try {
            Escanteio escanteio = escanteioService.obterEscanteioPorNumEvento(numEvento);
            return ResponseEntity.ok(escanteio);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarEscanteio(@PathVariable Integer numEvento) {
        try {
            escanteioService.excluirEscanteio(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir escanteio: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = escanteioService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirEscanteio(@RequestBody Escanteio escanteio) {
        try {
            escanteioService.inserirEscanteio(escanteio);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir escanteio: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarEscanteio(@RequestBody Escanteio escanteio, @PathVariable Integer numEvento) {
        try {
            escanteioService.atualizarEscanteio(escanteio, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar escanteio: " + e.getMessage());
        }
    }
}
