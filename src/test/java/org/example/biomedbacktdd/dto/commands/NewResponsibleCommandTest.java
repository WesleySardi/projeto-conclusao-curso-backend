package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewResponsibleCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewResponsibleCommand command = new NewResponsibleCommand();

        // Then
        assertThat(command.getKey()).isNull();
        assertThat(command.getNomeRes()).isNull();
        assertThat(command.getIdadeRes()).isNull();
        assertThat(command.getContato1Res()).isNull();
        assertThat(command.getContato2Res()).isNull();
        assertThat(command.getContato3Res()).isNull();
        assertThat(command.getPlanoAssinado()).isNull();
        assertThat(command.getEmailRes()).isNull();
        assertThat(command.getRgRes()).isNull();
        assertThat(command.getCepRes()).isNull();
        assertThat(command.getLogradouro()).isNull();
        assertThat(command.getNumero()).isNull();
        assertThat(command.getComplemento()).isNull();
        assertThat(command.getBairro()).isNull();
        assertThat(command.getCidade()).isNull();
        assertThat(command.getEstado()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewResponsibleCommand command = getNewResponsibleCommand();

        // Then
        assertThat(command.getKey()).isEqualTo("12345678900");
        assertThat(command.getNomeRes()).isEqualTo("John Doe");
        assertThat(command.getIdadeRes()).isEqualTo(40);
        assertThat(command.getContato1Res()).isEqualTo("123456789");
        assertThat(command.getContato2Res()).isEqualTo("987654321");
        assertThat(command.getContato3Res()).isEqualTo("555555555");
        assertThat(command.getPlanoAssinado()).isEqualTo(1);
        assertThat(command.getEmailRes()).isEqualTo("john.doe@example.com");
        assertThat(command.getRgRes()).isEqualTo("MG1234567");
        assertThat(command.getCepRes()).isEqualTo("12345000");
        assertThat(command.getLogradouro()).isEqualTo("Main Street");
        assertThat(command.getNumero()).isEqualTo(123);
        assertThat(command.getComplemento()).isEqualTo("Apartment 4B");
        assertThat(command.getBairro()).isEqualTo("Central District");
        assertThat(command.getCidade()).isEqualTo("Metropolis");
        assertThat(command.getEstado()).isEqualTo("MG");
    }

    private static NewResponsibleCommand getNewResponsibleCommand() {
        NewResponsibleCommand command = new NewResponsibleCommand();
        command.setKey("12345678900");
        command.setNomeRes("John Doe");
        command.setIdadeRes(40);
        command.setContato1Res("123456789");
        command.setContato2Res("987654321");
        command.setContato3Res("555555555");
        command.setPlanoAssinado(1);
        command.setEmailRes("john.doe@example.com");
        command.setRgRes("MG1234567");
        command.setCepRes("12345000");
        command.setLogradouro("Main Street");
        command.setNumero(123);
        command.setComplemento("Apartment 4B");
        command.setBairro("Central District");
        command.setCidade("Metropolis");
        command.setEstado("MG");
        return command;
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewResponsibleCommand command1 = new NewResponsibleCommand();
        command1.setKey("12345678900");
        command1.setNomeRes("John Doe");

        NewResponsibleCommand command2 = new NewResponsibleCommand();
        command2.setKey("12345678900");
        command2.setNomeRes("John Doe");

        NewResponsibleCommand command3 = new NewResponsibleCommand();
        command3.setKey("98765432100");
        command3.setNomeRes("Jane Doe");

        // Then
        assertThat(command1).isEqualTo(command2);
        assertThat(command1).hasSameHashCodeAs(command2);
        assertThat(command1).isNotEqualTo(command3);
    }
}
