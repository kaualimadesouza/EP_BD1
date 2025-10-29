package org.example.goalytics.controller;

import org.example.goalytics.model.CampeonatoEquipe;
import org.example.goalytics.service.CampeonatoEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/campeonato-equipe")
@CrossOrigin(origins = "*")
public class CampeonatoEquipeController {

    private final CampeonatoEquipeService campeonatoEquipeService;

    public CampeonatoEquipeController(CampeonatoEquipeService campeonatoEquipeService) {
        this.campeonatoEquipeService = campeonatoEquipeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<CampeonatoEquipe> listarCampeonatoEquipes() {
        return campeonatoEquipeService.listarCampeonatoEquipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoEquipe> obterCampeonatoEquipePorId(@PathVariable Integer id) {
        try {
            CampeonatoEquipe campeonatoEquipe = campeonatoEquipeService.obterCampeonatoEquipePorId(id);
            return ResponseEntity.ok(campeonatoEquipe);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCampeonatoEquipe(@PathVariable Integer id) {
        try {
            campeonatoEquipeService.excluirCampeonatoEquipe(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir campeonato_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = campeonatoEquipeService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirCampeonatoEquipe(@RequestBody CampeonatoEquipe campeonatoEquipe) {
        try {
            campeonatoEquipeService.inserirCampeonatoEquipe(campeonatoEquipe);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir campeonato_equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCampeonatoEquipe(@RequestBody CampeonatoEquipe campeonatoEquipe, @PathVariable Integer id) {
        try {
            campeonatoEquipeService.atualizarCampeonatoEquipe(campeonatoEquipe, id);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar campeonato_equipe: " + e.getMessage());
        }
    }
}
