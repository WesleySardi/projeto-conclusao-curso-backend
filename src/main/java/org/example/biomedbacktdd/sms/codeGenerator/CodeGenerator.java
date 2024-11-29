package org.example.biomedbacktdd.sms.codeGenerator;

import java.util.Random;

public class CodeGenerator {
    private static final Random random = new Random();

    public static String gerarCodigoSMS(int tamanho) {
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int digito = random.nextInt(10);
            codigo.append(digito);
        }

        return codigo.toString();
    }
}