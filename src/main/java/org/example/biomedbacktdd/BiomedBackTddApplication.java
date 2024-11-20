package org.example.biomedbacktdd;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableWebMvc
public class BiomedBackTddApplication {

    public static void main(String[] args) {
        try {
            initializeFirebase();
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o Firebase: " + e.getMessage());
        }

        SpringApplication.run(BiomedBackTddApplication.class, args);
    }

    private static void initializeFirebase() throws IOException {
        String credentialsJson = System.getenv("FIREBASE_CREDENTIALS");

        if (credentialsJson == null || credentialsJson.isEmpty()) {
            throw new IllegalStateException("A variável de ambiente FIREBASE_CREDENTIALS não está definida.");
        }

        ByteArrayInputStream credentialsStream = new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase inicializado com sucesso.");
        } else {
            System.out.println("Firebase já está inicializado.");
        }
    }
}
