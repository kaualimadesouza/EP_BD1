package org.example.goalytics.controller;

import org.example.goalytics.Records.PartidaDetalhesDTO;
import org.example.goalytics.model.Partida;
import org.example.goalytics.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Partida> listarPartidas() {
        return partidaService.listarPartidas();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Partida> obterPartidaPorId(@PathVariable Integer id) {
        try {
            Partida partida = partidaService.obterPartidaPorId(id);
            return ResponseEntity.ok(partida);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPartida(@PathVariable Integer id) {
        try {
            partidaService.excluirPartida(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrada")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir partida: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = partidaService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirPartida(@RequestBody Partida partida) {
        try {
            partidaService.inserirPartida(partida);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir partida: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPartida(@RequestBody Partida partida, @PathVariable Integer id) {
        try {
            partidaService.atualizarPartida(partida, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar partida: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("detalhes/ultimas")
    public ResponseEntity<List<PartidaDetalhesDTO>> obterUltimaPartidaDetalhes() {
        try {
            List<PartidaDetalhesDTO> partidas = partidaService.obterUltimaPartidaDetalhes();
            return ResponseEntity.ok(partidas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

