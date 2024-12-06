package org.example.biomedbacktdd.sms.codegenerator;

import java.security.SecureRandom;

public class CodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String gerarCodigoSMS(int tamanho) {
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int digito = secureRandom.nextInt(10);
            codigo.append(digito);
        }

        return codigo.toString();
    }
}
