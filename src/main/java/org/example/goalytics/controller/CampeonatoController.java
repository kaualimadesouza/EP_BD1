package org.example.goalytics.controller;

import org.example.goalytics.Records.ArtilheirosCampeonatoDTO;
import org.example.goalytics.Records.CampeonatoHistoricoPartidasDTO;
import org.example.goalytics.Records.EstatisticasCampeonatoDTO;
import org.example.goalytics.model.Campeonato;
import org.example.goalytics.service.CampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {
    private final CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Campeonato> listarCampeonatos() {
        return campeonatoService.listarCampeonatos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Campeonato> obterCampeonatoPorId(@PathVariable Integer id) {
        try {
            Campeonato campeonato = campeonatoService.obterCampeonatoPorId(id);
            return ResponseEntity.ok(campeonato);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/jogos_recentes")
    public ResponseEntity<List<CampeonatoHistoricoPartidasDTO>> listarCampeonatosComJogosRecentes() {
        try {
            List<CampeonatoHistoricoPartidasDTO> campeonatos = campeonatoService.listarCampeonatosComJogosRecentes();
            return ResponseEntity.ok(campeonatos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/artilheiros")
    public ResponseEntity<List<ArtilheirosCampeonatoDTO>> artilheirosPorCampeonato() {
        try {
            List<ArtilheirosCampeonatoDTO> artilheiros = campeonatoService.listarArtilheiroPorCampeonato();
            return ResponseEntity.ok(artilheiros);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/estatisticas/{nomeCampeonato}")
    public ResponseEntity<EstatisticasCampeonatoDTO> estatisticasPorNomeCampeonato(@PathVariable String nomeCampeonato) {
        try {
            EstatisticasCampeonatoDTO estatisticas = campeonatoService.buscarEstatisticasPorNomeCampeonato();
            return ResponseEntity.ok(estatisticas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCampeonato(@PathVariable Integer id) {
        try {
            campeonatoService.excluirCampeonato(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(404).body(e.getMessage());
            }
            return ResponseEntity.status(500).body("Erro ao excluir campeonato: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/columns")
    public ResponseEntity<List<String>> obterColunas() {
        try {
            List<String> colunas = campeonatoService.obterColunas();
            return ResponseEntity.ok(colunas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Erro ao adicionar coluna: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<String> inserirCampeonato(@RequestBody Campeonato campeonato) {
        try {
            campeonatoService.inserirCampeonato(campeonato);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao inserir campeonato: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCampeonato(@RequestBody Campeonato campeonato, @PathVariable Integer id) {
        try {
            campeonatoService.atualizarCampeonato(campeonato, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro ao atualizar campeonato: " + e.getMessage());
        }
    }
}

