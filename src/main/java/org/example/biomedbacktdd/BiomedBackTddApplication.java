package org.example.biomedbacktdd;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.example.biomedbacktdd.s3.S3Service;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@SpringBootApplication
@EnableWebMvc
public class BiomedBackTddApplication {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BiomedBackTddApplication.class);

    private static final String BUCKET_NAME = "biomed-firebase-credentials";
    private static final String CREDENTIALS_FILE_KEY = "firebase-credentials.json";


    public static void main(String[] args) {
        try {
            initializeFirebase();
        } catch (IOException e) {
            logger.info("Erro ao inicializar o Firebase");
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
            logger.info("Firebase inicializado com sucesso.");
        } else {
            logger.info("Firebase já está inicializado.");
        }
    }
}
