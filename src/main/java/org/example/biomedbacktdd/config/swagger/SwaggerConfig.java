package org.example.biomedbacktdd.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "API Documentation", version = "v1"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return openApi -> {
            Paths paths = openApi.getPaths();
            if (paths != null) {
                Paths filteredPaths = new Paths();

                for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
                    String path = entry.getKey();
                    if (path.startsWith("/api/") || path.startsWith("/auth/")) {
                        filteredPaths.addPathItem(path, entry.getValue());
                    }
                }

                openApi.setPaths(filteredPaths);
            }
        };
    }
}
