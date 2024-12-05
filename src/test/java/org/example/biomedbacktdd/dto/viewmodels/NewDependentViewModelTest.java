package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewDependentViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewDependentViewModel viewModel = new NewDependentViewModel();

        // Then
        assertThat(viewModel.getKey()).isNull();
        assertThat(viewModel.getNomeDep()).isNull();
        assertThat(viewModel.getIdadeDep()).isNull();
        assertThat(viewModel.getTipoSanguineo()).isNull();
        assertThat(viewModel.getLaudo()).isNull();
        assertThat(viewModel.getGeneroDep()).isNull();
        assertThat(viewModel.getRgDep()).isNull();
        assertThat(viewModel.getCpfResDep()).isNull();
        assertThat(viewModel.getPiTagIdDep()).isNull();
        assertThat(viewModel.getCpfTerDep()).isNull();
        assertThat(viewModel.getIdCirurgiaDep()).isNull();
        assertThat(viewModel.getIdScanDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewDependentViewModel viewModel = new NewDependentViewModel();
        viewModel.setKey("12345678900");
        viewModel.setNomeDep("Jane Doe");
        viewModel.setIdadeDep(30);
        viewModel.setTipoSanguineo("O+");
        viewModel.setLaudo("Healthy");
        viewModel.setGeneroDep("Female");
        viewModel.setRgDep("MG1234567");
        viewModel.setCpfResDep("98765432100");
        viewModel.setPiTagIdDep(123);
        viewModel.setCpfTerDep("12312312300");
        viewModel.setIdCirurgiaDep(1);
        viewModel.setIdScanDep(5);

        // Then
        assertThat(viewModel.getKey()).isEqualTo("12345678900");
        assertThat(viewModel.getNomeDep()).isEqualTo("Jane Doe");
        assertThat(viewModel.getIdadeDep()).isEqualTo(30);
        assertThat(viewModel.getTipoSanguineo()).isEqualTo("O+");
        assertThat(viewModel.getLaudo()).isEqualTo("Healthy");
        assertThat(viewModel.getGeneroDep()).isEqualTo("Female");
        assertThat(viewModel.getRgDep()).isEqualTo("MG1234567");
        assertThat(viewModel.getCpfResDep()).isEqualTo("98765432100");
        assertThat(viewModel.getPiTagIdDep()).isEqualTo(123);
        assertThat(viewModel.getCpfTerDep()).isEqualTo("12312312300");
        assertThat(viewModel.getIdCirurgiaDep()).isEqualTo(1);
        assertThat(viewModel.getIdScanDep()).isEqualTo(5);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewDependentViewModel viewModel1 = new NewDependentViewModel();
        viewModel1.setKey("12345678900");
        viewModel1.setNomeDep("Jane Doe");

        NewDependentViewModel viewModel2 = new NewDependentViewModel();
        viewModel2.setKey("12345678900");
        viewModel2.setNomeDep("Jane Doe");

        NewDependentViewModel viewModel3 = new NewDependentViewModel();
        viewModel3.setKey("98765432100");
        viewModel3.setNomeDep("John Doe");

        // Then
        assertThat(viewModel1).isEqualTo(viewModel2);
        assertThat(viewModel1).hasSameHashCodeAs(viewModel2);
        assertThat(viewModel1).isNotEqualTo(viewModel3);
    }
}
