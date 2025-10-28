package org.example.goalytics.controller;

import org.example.goalytics.service.EstadioService;
import org.example.goalytics.service.SchemaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SchemaController {

    private final SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/tables")
    public List<String> getAllTablesDatabase() {
        return schemaService.listAllTables();
    }
}
