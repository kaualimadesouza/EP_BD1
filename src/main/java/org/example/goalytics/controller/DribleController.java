package org.example.goalytics.controller;

import org.example.goalytics.model.Drible;
import org.example.goalytics.service.DribleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/drible")
@CrossOrigin(origins = "*")
public class DribleController {

    private final DribleService dribleService;

    public DribleController(DribleService dribleService) {
        this.dribleService = dribleService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Drible> listarDribles() {
        return dribleService.listarDribles();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{numEvento}")
    public ResponseEntity<Drible> obterDriblePorNumEvento(@PathVariable Integer numEvento) {
        try {
            Drible drible = dribleService.obterDriblePorNumEvento(numEvento);
            return ResponseEntity.ok(drible);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{numEvento}")
    public ResponseEntity<String> deletarDrible(@PathVariable Integer numEvento) {
        try {
            dribleService.excluirDrible(numEvento);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir drible: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = dribleService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirDrible(@RequestBody Drible drible) {
        try {
            dribleService.inserirDrible(drible);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir drible: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{numEvento}")
    public ResponseEntity<String> atualizarDrible(@RequestBody Drible drible, @PathVariable Integer numEvento) {
        try {
            dribleService.atualizarDrible(drible, numEvento);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar drible: " + e.getMessage());
        }
    }
}
