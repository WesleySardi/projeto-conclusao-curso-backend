package org.example.biomedbacktdd.dto.commands;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewDependentCommandTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewDependentCommand command = new NewDependentCommand();

        // Then
        assertThat(command.getKey()).isNull();
        assertThat(command.getNomeDep()).isNull();
        assertThat(command.getIdadeDep()).isNull();
        assertThat(command.getTipoSanguineo()).isNull();
        assertThat(command.getLaudo()).isNull();
        assertThat(command.getGeneroDep()).isNull();
        assertThat(command.getRgDep()).isNull();
        assertThat(command.getCpfResDep()).isNull();
        assertThat(command.getPiTagIdDep()).isNull();
        assertThat(command.getCpfTerDep()).isNull();
        assertThat(command.getIdCirurgiaDep()).isNull();
        assertThat(command.getIdScanDep()).isNull();
    }

    @Test
    void testSetAndGetAttributes() {
        // Given
        NewDependentCommand command = getNewDependentCommand();

        // Then
        assertThat(command.getKey()).isEqualTo("12345678900");
        assertThat(command.getNomeDep()).isEqualTo("John Doe");
        assertThat(command.getIdadeDep()).isEqualTo(25);
        assertThat(command.getTipoSanguineo()).isEqualTo("O+");
        assertThat(command.getLaudo()).isEqualTo("Healthy");
        assertThat(command.getGeneroDep()).isEqualTo("Male");
        assertThat(command.getRgDep()).isEqualTo("MG1234567");
        assertThat(command.getCpfResDep()).isEqualTo("98765432100");
        assertThat(command.getPiTagIdDep()).isEqualTo(123);
        assertThat(command.getCpfTerDep()).isEqualTo("12312312300");
        assertThat(command.getIdCirurgiaDep()).isEqualTo(1);
        assertThat(command.getIdScanDep()).isEqualTo(5);
    }

    private static NewDependentCommand getNewDependentCommand() {
        NewDependentCommand command = new NewDependentCommand();
        command.setKey("12345678900");
        command.setNomeDep("John Doe");
        command.setIdadeDep(25);
        command.setTipoSanguineo("O+");
        command.setLaudo("Healthy");
        command.setGeneroDep("Male");
        command.setRgDep("MG1234567");
        command.setCpfResDep("98765432100");
        command.setPiTagIdDep(123);
        command.setCpfTerDep("12312312300");
        command.setIdCirurgiaDep(1);
        command.setIdScanDep(5);
        return command;
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewDependentCommand command1 = new NewDependentCommand();
        command1.setKey("123");
        command1.setNomeDep("John");

        NewDependentCommand command2 = new NewDependentCommand();
        command2.setKey("123");
        command2.setNomeDep("John");

        NewDependentCommand command3 = new NewDependentCommand();
        command3.setKey("456");
        command3.setNomeDep("Jane");

        // Then
        assertThat(command1).isEqualTo(command2);
        assertThat(command1).hasSameHashCodeAs(command2);
        assertThat(command1).isNotEqualTo(command3);
    }
}
