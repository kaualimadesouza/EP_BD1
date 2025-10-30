package org.example.goalytics.controller;

import org.example.goalytics.Records.EquipePartidaDTO;
import org.example.goalytics.model.Equipe;
import org.example.goalytics.service.EquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/equipe")
public class EquipeController {
    private static final Logger logger = LoggerFactory.getLogger(EquipeController.class);
    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Equipe> listarEquipes() {
        logger.info("Requisição recebida: listarEquipes()");
        return equipeService.listarEquipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Equipe> obterEquipePorId(@PathVariable Integer id) {
        logger.info("Requisição recebida: obterEquipePorId({})", id);
        try {
            Equipe equipe = equipeService.obterEquipePorId(id);
            return ResponseEntity.ok(equipe);
        } catch (RuntimeException e) {
            logger.error("Erro ao obter equipe por id {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEquipe(@PathVariable Integer id) {
        logger.info("Requisição recebida: deletarEquipe({})", id);
        try {
            equipeService.excluirEquipe(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Erro ao excluir equipe {}: {}", id, e.getMessage());
            if (e.getMessage() != null && e.getMessage().contains("não encontrada")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        logger.info("Requisição recebida: obterColunas()");
        try {
            List<String> colunas = equipeService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            logger.error("Erro ao obter colunas: {}", e.getMessage());
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirEquipe(@RequestBody Equipe equipe) {
        logger.info("Requisição recebida: inserirEquipe({})", equipe);
        try {
            equipeService.inserirEquipe(equipe);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Erro ao inserir equipe: {}", e.getMessage());
            return ResponseEntity.status(500).body("Erro ao inserir equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarEquipe(@RequestBody Equipe equipe, @PathVariable Integer id) {
        logger.info("Requisição recebida: atualizarEquipe({}, {})", id, equipe);
        try {
            equipeService.atualizarEquipe(equipe, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Erro ao atualizar equipe {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body("Erro ao atualizar equipe: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/partida/{id}")
    public ResponseEntity<List<EquipePartidaDTO>> obterEquipesPorPartidaId(@PathVariable Integer id) {
        logger.info("Requisição recebida: obterEquipesPorPartidaId({})", id);
        try {
            List<EquipePartidaDTO> equipes = equipeService.obterEquipesPorPartidaId(id);
            logger.info(equipes.toString());
            return ResponseEntity.ok(equipes);
        } catch (RuntimeException e) {
            logger.error("Erro ao obter equipes por partida id {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

}
