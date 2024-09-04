package org.example.biomedbacktdd.controllers.mixed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mixed")
@Tag(name = "Mixed", description = "Endpoints para lidar com Dependentes e Responsáveis.")
public class MixedController {

    @Operation(summary = "Saudação simples", description = "Retorna uma saudação simples 'Hello, World!'")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
