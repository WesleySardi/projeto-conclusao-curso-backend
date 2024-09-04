package org.example.biomedbacktdd.controllers.dependent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dependent")
@Tag(name = "Dependent", description = "Endpoints para lidar com Dependentes.")
public class DependentController {

    @Operation(summary = "Saudação simples", description = "Retorna uma saudação simples 'Hello, World!'")
    @GetMapping("/commonuser/hello")
    public String hello() {
        return "Hello, World!";
    }
}