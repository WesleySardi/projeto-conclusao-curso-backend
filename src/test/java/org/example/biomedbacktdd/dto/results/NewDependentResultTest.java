package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewDependentResultTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewDependentResult result = new NewDependentResult();

        // Then
        assertThat(result.getKey()).isNull();
        assertThat(result.getNomeDep()).isNull();
        assertThat(result.getIdadeDep()).isNull();
        assertThat(result.getTipoSanguineo()).isNull();
        assertThat(result.getLaudo()).isNull();
        assertThat(result.getGeneroDep()).isNull();
        assertThat(result.getRgDep()).isNull();
        assertThat(result.getCpfResDep()).isNull();
        assertThat(result.getPiTagIdDep()).isNull();
        assertThat(result.getCpfTerDep()).isNull();
        assertThat(result.getIdCirurgiaDep()).isNull();
        assertThat(result.getIdScanDep()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewDependentResult result = new NewDependentResult();
        result.setKey("12345678900");
        result.setNomeDep("John Doe");
        result.setIdadeDep(30);
        result.setTipoSanguineo("A+");
        result.setLaudo("Healthy");
        result.setGeneroDep("Male");
        result.setRgDep("MG1234567");
        result.setCpfResDep("98765432100");
        result.setPiTagIdDep(456);
        result.setCpfTerDep("12345678911");
        result.setIdCirurgiaDep(2);
        result.setIdScanDep(10);

        // Then
        assertThat(result.getKey()).isEqualTo("12345678900");
        assertThat(result.getNomeDep()).isEqualTo("John Doe");
        assertThat(result.getIdadeDep()).isEqualTo(30);
        assertThat(result.getTipoSanguineo()).isEqualTo("A+");
        assertThat(result.getLaudo()).isEqualTo("Healthy");
        assertThat(result.getGeneroDep()).isEqualTo("Male");
        assertThat(result.getRgDep()).isEqualTo("MG1234567");
        assertThat(result.getCpfResDep()).isEqualTo("98765432100");
        assertThat(result.getPiTagIdDep()).isEqualTo(456);
        assertThat(result.getCpfTerDep()).isEqualTo("12345678911");
        assertThat(result.getIdCirurgiaDep()).isEqualTo(2);
        assertThat(result.getIdScanDep()).isEqualTo(10);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewDependentResult result1 = new NewDependentResult();
        result1.setKey("12345678900");
        result1.setNomeDep("John Doe");

        NewDependentResult result2 = new NewDependentResult();
        result2.setKey("12345678900");
        result2.setNomeDep("John Doe");

        NewDependentResult result3 = new NewDependentResult();
        result3.setKey("98765432100");
        result3.setNomeDep("Jane Doe");

        // Then
        assertThat(result1).isEqualTo(result2);
        assertThat(result1).hasSameHashCodeAs(result2);
        assertThat(result1).isNotEqualTo(result3);
    }
}
