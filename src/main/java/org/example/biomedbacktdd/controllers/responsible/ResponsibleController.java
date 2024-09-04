package org.example.biomedbacktdd.controllers.responsible;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/responsible")
@Tag(name = "Responsible", description = "Endpoints para lidar com Responsáveis.")
public class ResponsibleController {

    @Operation(summary = "Saudação simples", description = "Retorna uma saudação simples 'Hello, World!'")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
