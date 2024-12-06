package org.example.biomedbacktdd.dto.results;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewResponsibleResultTest {

    @Test
    void testEmptyConstructor() {
        // When
        NewResponsibleResult result = new NewResponsibleResult();

        // Then
        assertThat(result.getKey()).isNull();
        assertThat(result.getNomeRes()).isNull();
        assertThat(result.getIdadeRes()).isNull();
        assertThat(result.getContato1Res()).isNull();
        assertThat(result.getContato2Res()).isNull();
        assertThat(result.getContato3Res()).isNull();
        assertThat(result.getPlanoAssinado()).isNull();
        assertThat(result.getEmailRes()).isNull();
        assertThat(result.getRgRes()).isNull();
        assertThat(result.getCepRes()).isNull();
        assertThat(result.getLogradouro()).isNull();
        assertThat(result.getNumero()).isNull();
        assertThat(result.getComplemento()).isNull();
        assertThat(result.getBairro()).isNull();
        assertThat(result.getCidade()).isNull();
        assertThat(result.getEstado()).isNull();
    }

    @Test
    void testSettersAndGetters() {
        // Given
        NewResponsibleResult result = new NewResponsibleResult();
        result.setKey("12345678900");
        result.setNomeRes("Jane Doe");
        result.setIdadeRes(45);
        result.setContato1Res("123456789");
        result.setContato2Res("987654321");
        result.setContato3Res("555555555");
        result.setPlanoAssinado(1);
        result.setEmailRes("jane.doe@example.com");
        result.setRgRes("MG1234567");
        result.setCepRes("12345000");
        result.setLogradouro("Main Street");
        result.setNumero(456);
        result.setComplemento("Apartment 3A");
        result.setBairro("Downtown");
        result.setCidade("Metropolis");
        result.setEstado("MG");

        // Then
        assertThat(result.getKey()).isEqualTo("12345678900");
        assertThat(result.getNomeRes()).isEqualTo("Jane Doe");
        assertThat(result.getIdadeRes()).isEqualTo(45);
        assertThat(result.getContato1Res()).isEqualTo("123456789");
        assertThat(result.getContato2Res()).isEqualTo("987654321");
        assertThat(result.getContato3Res()).isEqualTo("555555555");
        assertThat(result.getPlanoAssinado()).isEqualTo(1);
        assertThat(result.getEmailRes()).isEqualTo("jane.doe@example.com");
        assertThat(result.getRgRes()).isEqualTo("MG1234567");
        assertThat(result.getCepRes()).isEqualTo("12345000");
        assertThat(result.getLogradouro()).isEqualTo("Main Street");
        assertThat(result.getNumero()).isEqualTo(456);
        assertThat(result.getComplemento()).isEqualTo("Apartment 3A");
        assertThat(result.getBairro()).isEqualTo("Downtown");
        assertThat(result.getCidade()).isEqualTo("Metropolis");
        assertThat(result.getEstado()).isEqualTo("MG");
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        NewResponsibleResult result1 = new NewResponsibleResult();
        result1.setKey("12345678900");
        result1.setNomeRes("Jane Doe");

        NewResponsibleResult result2 = new NewResponsibleResult();
        result2.setKey("12345678900");
        result2.setNomeRes("Jane Doe");

        NewResponsibleResult result3 = new NewResponsibleResult();
        result3.setKey("98765432100");
        result3.setNomeRes("John Doe");

        // Then
        assertThat(result1).isEqualTo(result2);
        assertThat(result1).hasSameHashCodeAs(result2);
        assertThat(result1).isNotEqualTo(result3);
    }

}
