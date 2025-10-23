package org.example.goalytics.controller;

import org.example.goalytics.model.Estadio;
import org.example.goalytics.service.EstadioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estadio")
public class EstadioController {

    private final EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @GetMapping
    public List<Estadio> listarEstadios() {
        return estadioService.listarEstadios();
    }
}
