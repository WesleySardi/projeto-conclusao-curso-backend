package org.example.biomedbacktdd.dto.viewmodels;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DependentNameViewModelTest {

    @Test
    void testEmptyConstructor() {
        // When
        DependentNameViewModel viewModel = new DependentNameViewModel();

        // Then
        assertThat(viewModel.getNomeDep()).isNull();
    }

    @Test
    void testConstructorWithNomeDep() {
        // Given
        String nomeDep = "John Doe";

        // When
        DependentNameViewModel viewModel = new DependentNameViewModel(nomeDep);

        // Then
        assertThat(viewModel.getNomeDep()).isEqualTo(nomeDep);
    }

    @Test
    void testSetNomeDep() {
        // Given
        DependentNameViewModel viewModel = new DependentNameViewModel();
        String nomeDep = "Jane Doe";

        // When
        viewModel.setNomeDep(nomeDep);

        // Then
        assertThat(viewModel.getNomeDep()).isEqualTo(nomeDep);
    }
}
