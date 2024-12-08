package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationStorageResultTest {

    @Test
    void testEmptyConstructor() {
        // When
        NotificationStorageResult result = new NotificationStorageResult();

        // Then
        assertThat(result.getIdNotificacao()).isEqualTo(0);
        assertThat(result.getTitulo()).isNull();
        assertThat(result.getMensagem()).isNull();
        assertThat(result.getCpfResponsavel()).isNull();
        assertThat(result.getDataEnvio()).isNull();
        assertThat(result.getLida()).isNull();
        assertThat(result.getCpfDependente()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NotificationStorageResult result = new NotificationStorageResult();
        int idNotificacao = 101;
        String titulo = "Notification Title";
        String mensagem = "This is the message content.";
        String cpfResponsavel = "12345678900";
        ZonedDateTime dataEnvio = ZonedDateTime.now();
        Boolean lida = true;
        String cpfDependente = "98765432100";

        // When
        result.setIdNotificacao(idNotificacao);
        result.setTitulo(titulo);
        result.setMensagem(mensagem);
        result.setCpfResponsavel(cpfResponsavel);
        result.setDataEnvio(dataEnvio);
        result.setLida(lida);
        result.setCpfDependente(cpfDependente);

        // Then
        assertThat(result.getIdNotificacao()).isEqualTo(idNotificacao);
        assertThat(result.getTitulo()).isEqualTo(titulo);
        assertThat(result.getMensagem()).isEqualTo(mensagem);
        assertThat(result.getCpfResponsavel()).isEqualTo(cpfResponsavel);
        assertThat(result.getDataEnvio()).isEqualTo(dataEnvio);
        assertThat(result.getLida()).isEqualTo(lida);
        assertThat(result.getCpfDependente()).isEqualTo(cpfDependente);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        ZonedDateTime now = ZonedDateTime.now();

        NotificationStorageResult result1 = new NotificationStorageResult();
        result1.setIdNotificacao(1);
        result1.setTitulo("Title");
        result1.setMensagem("Message");
        result1.setCpfResponsavel("12345678900");
        result1.setDataEnvio(now);
        result1.setLida(true);
        result1.setCpfDependente("98765432100");

        NotificationStorageResult result2 = new NotificationStorageResult();
        result2.setIdNotificacao(1);
        result2.setTitulo("Title");
        result2.setMensagem("Message");
        result2.setCpfResponsavel("12345678900");
        result2.setDataEnvio(now);
        result2.setLida(true);
        result2.setCpfDependente("98765432100");

        NotificationStorageResult result3 = new NotificationStorageResult();
        result3.setIdNotificacao(2);
        result3.setTitulo("Different Title");
        result3.setMensagem("Different Message");
        result3.setCpfResponsavel("98765432100");
        result3.setDataEnvio(now);
        result3.setLida(false);
        result3.setCpfDependente("12345678900");

        // Then
        assertThat(result1).isEqualTo(result2);
        assertThat(result1).hasSameHashCodeAs(result2);
        assertThat(result1).isNotEqualTo(result3);
    }

    @Test
    void testToString() {
        // Given
        ZonedDateTime now = ZonedDateTime.now();
        NotificationStorageResult result = new NotificationStorageResult();
        result.setIdNotificacao(101);
        result.setTitulo("Notification Title");
        result.setMensagem("Notification message content.");
        result.setCpfResponsavel("12345678900");
        result.setDataEnvio(now);
        result.setLida(true);
        result.setCpfDependente("98765432100");

        // Then
        assertThat(result.toString()).contains(
                "101",
                "Notification Title",
                "Notification message content.",
                "12345678900",
                now.toString(),
                "true",
                "98765432100"
        );
    }
}
