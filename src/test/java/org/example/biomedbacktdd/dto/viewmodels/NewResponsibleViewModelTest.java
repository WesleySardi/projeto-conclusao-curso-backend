package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewResponsibleViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewResponsibleViewModel viewModel = new NewResponsibleViewModel();

        // Then
        assertThat(viewModel.getKey()).isNull();
        assertThat(viewModel.getNomeRes()).isNull();
        assertThat(viewModel.getIdadeRes()).isNull();
        assertThat(viewModel.getContato1Res()).isNull();
        assertThat(viewModel.getContato2Res()).isNull();
        assertThat(viewModel.getContato3Res()).isNull();
        assertThat(viewModel.getPlanoAssinado()).isNull();
        assertThat(viewModel.getEmailRes()).isNull();
        assertThat(viewModel.getRgRes()).isNull();
        assertThat(viewModel.getCepRes()).isNull();
        assertThat(viewModel.getLogradouro()).isNull();
        assertThat(viewModel.getNumero()).isNull();
        assertThat(viewModel.getComplemento()).isNull();
        assertThat(viewModel.getBairro()).isNull();
        assertThat(viewModel.getCidade()).isNull();
        assertThat(viewModel.getEstado()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewResponsibleViewModel viewModel = new NewResponsibleViewModel();
        viewModel.setKey("12345678900");
        viewModel.setNomeRes("Jane Doe");
        viewModel.setIdadeRes(40);
        viewModel.setContato1Res("123456789");
        viewModel.setContato2Res("987654321");
        viewModel.setContato3Res("555555555");
        viewModel.setPlanoAssinado(1);
        viewModel.setEmailRes("jane.doe@example.com");
        viewModel.setRgRes("MG1234567");
        viewModel.setCepRes("12345000");
        viewModel.setLogradouro("Main Street");
        viewModel.setNumero(456);
        viewModel.setComplemento("Apartment 3A");
        viewModel.setBairro("Downtown");
        viewModel.setCidade("Metropolis");
        viewModel.setEstado("MG");

        // Then
        assertThat(viewModel.getKey()).isEqualTo("12345678900");
        assertThat(viewModel.getNomeRes()).isEqualTo("Jane Doe");
        assertThat(viewModel.getIdadeRes()).isEqualTo(40);
        assertThat(viewModel.getContato1Res()).isEqualTo("123456789");
        assertThat(viewModel.getContato2Res()).isEqualTo("987654321");
        assertThat(viewModel.getContato3Res()).isEqualTo("555555555");
        assertThat(viewModel.getPlanoAssinado()).isEqualTo(1);
        assertThat(viewModel.getEmailRes()).isEqualTo("jane.doe@example.com");
        assertThat(viewModel.getRgRes()).isEqualTo("MG1234567");
        assertThat(viewModel.getCepRes()).isEqualTo("12345000");
        assertThat(viewModel.getLogradouro()).isEqualTo("Main Street");
        assertThat(viewModel.getNumero()).isEqualTo(456);
        assertThat(viewModel.getComplemento()).isEqualTo("Apartment 3A");
        assertThat(viewModel.getBairro()).isEqualTo("Downtown");
        assertThat(viewModel.getCidade()).isEqualTo("Metropolis");
        assertThat(viewModel.getEstado()).isEqualTo("MG");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewResponsibleViewModel viewModel1 = new NewResponsibleViewModel();
        viewModel1.setKey("12345678900");
        viewModel1.setNomeRes("Jane Doe");

        NewResponsibleViewModel viewModel2 = new NewResponsibleViewModel();
        viewModel2.setKey("12345678900");
        viewModel2.setNomeRes("Jane Doe");

        NewResponsibleViewModel viewModel3 = new NewResponsibleViewModel();
        viewModel3.setKey("98765432100");
        viewModel3.setNomeRes("John Doe");

        // Then
        assertThat(viewModel1).isEqualTo(viewModel2);
        assertThat(viewModel1).hasSameHashCodeAs(viewModel2);
        assertThat(viewModel1).isNotEqualTo(viewModel3);
    }
}
