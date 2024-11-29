package org.example.biomedbacktdd;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.example.biomedbacktdd.s3.S3Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@EnableWebMvc
public class BiomedBackTddApplication {

    private static final String BUCKET_NAME = "biomed-firebase-credentials";
    private static final String CREDENTIALS_FILE_KEY = "firebase-credentials.json";


    public static void main(String[] args) {
        try {
            initializeFirebase();
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o Firebase: " + e.getMessage());
        }

        SpringApplication.run(BiomedBackTddApplication.class, args);
    }

    private static void initializeFirebase() throws IOException {
        S3Service s3Service = new S3Service();

        InputStream credentialsStream = s3Service.downloadFile(BUCKET_NAME, CREDENTIALS_FILE_KEY);

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
