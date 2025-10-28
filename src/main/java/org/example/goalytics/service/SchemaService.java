package org.example.goalytics.service;

import org.example.goalytics.repository.SchemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaService {
    private final SchemaRepository schemaRepository;

    public SchemaService(SchemaRepository schemaRepository) {
        this.schemaRepository = schemaRepository;
    }

    public List<String> listAllTables() {
        return schemaRepository.listAllTables();
    }
}
