package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationStorageCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        NotificationStorageCommand command = new NotificationStorageCommand();

        // Then
        assertThat(command.getIdNotificacao()).isEqualTo(0);
        assertThat(command.getTitulo()).isNull();
        assertThat(command.getMensagem()).isNull();
        assertThat(command.getCpfResponsavel()).isNull();
        assertThat(command.getDataEnvio()).isNotNull();
        assertThat(command.getLida()).isNull();
        assertThat(command.getCpfDependente()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NotificationStorageCommand command = new NotificationStorageCommand();
        int idNotificacao = 101;
        String titulo = "Notification Title";
        String mensagem = "This is a notification message.";
        String cpfResponsavel = "12345678900";
        ZonedDateTime dataEnvio = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        Boolean lida = true;
        String cpfDependente = "98765432100";

        // When
        command.setIdNotificacao(idNotificacao);
        command.setTitulo(titulo);
        command.setMensagem(mensagem);
        command.setCpfResponsavel(cpfResponsavel);
        command.setDataEnvio(dataEnvio);
        command.setLida(lida);
        command.setCpfDependente(cpfDependente);

        // Then
        assertThat(command.getIdNotificacao()).isEqualTo(idNotificacao);
        assertThat(command.getTitulo()).isEqualTo(titulo);
        assertThat(command.getMensagem()).isEqualTo(mensagem);
        assertThat(command.getCpfResponsavel()).isEqualTo(cpfResponsavel);
        assertThat(command.getDataEnvio()).isEqualTo(dataEnvio);
        assertThat(command.getLida()).isEqualTo(lida);
        assertThat(command.getCpfDependente()).isEqualTo(cpfDependente);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        NotificationStorageCommand command1 = new NotificationStorageCommand();
        command1.setTitulo("Title");
        command1.setMensagem("Message");
        command1.setCpfResponsavel("12345678900");
        command1.setDataEnvio(now);
        command1.setLida(true);

        NotificationStorageCommand command2 = new NotificationStorageCommand();
        command2.setTitulo("Title");
        command2.setMensagem("Message");
        command2.setCpfResponsavel("12345678900");
        command2.setDataEnvio(now);
        command2.setLida(true);

        NotificationStorageCommand command3 = new NotificationStorageCommand();
        command3.setTitulo("Different Title");
        command3.setMensagem("Different Message");
        command3.setCpfResponsavel("98765432100");
        command3.setDataEnvio(now);
        command3.setLida(false);

        // Then
        assertThat(command1).isEqualTo(command2);
        assertThat(command1).hasSameHashCodeAs(command2);
        assertThat(command1).isNotEqualTo(command3);
    }

    @Test
    void testToString() {
        // Given
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("Notification Title");
        command.setMensagem("Notification message.");
        command.setCpfResponsavel("12345678900");
        command.setDataEnvio(now);
        command.setLida(true);

        // Then
        assertThat(command.toString()).contains(
                "NotificationStorageCommand",
                "Notification Title",
                "Notification message.",
                "12345678900",
                now.toString(),
                "true"
        );
    }
}
