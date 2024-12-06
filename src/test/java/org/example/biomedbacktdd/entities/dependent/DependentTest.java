package org.example.biomedbacktdd.entities.dependent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DependentTest {

    @Test
    void testGettersAndSetters() {
        Dependent dependent = new Dependent();

        dependent.setCpfDep("12345678900");
        assertEquals("12345678900", dependent.getCpfDep());

        dependent.setNomeDep("John Doe");
        assertEquals("John Doe", dependent.getNomeDep());

        dependent.setIdadeDep(25);
        assertEquals(25, dependent.getIdadeDep());

        dependent.setTipoSanguineo("O+");
        assertEquals("O+", dependent.getTipoSanguineo());

        dependent.setLaudo("Healthy");
        assertEquals("Healthy", dependent.getLaudo());

        dependent.setGeneroDep("Male");
        assertEquals("Male", dependent.getGeneroDep());

        dependent.setRgDep("MG1234567");
        assertEquals("MG1234567", dependent.getRgDep());

        dependent.setCpfResDep("98765432100");
        assertEquals("98765432100", dependent.getCpfResDep());

        dependent.setPiTagIdDep(101);
        assertEquals(101, dependent.getPiTagIdDep());

        dependent.setCpfTerDep("54321098765");
        assertEquals("54321098765", dependent.getCpfTerDep());

        dependent.setIdCirurgiaDep(5);
        assertEquals(5, dependent.getIdCirurgiaDep());

        dependent.setIdScanDep(7);
        assertEquals(7, dependent.getIdScanDep());
    }

    @Test
    void testEqualsAndHashCode() {
        Dependent dependent1 = new Dependent();
        dependent1.setCpfDep("12345678900");
        dependent1.setNomeDep("John Doe");

        Dependent dependent2 = new Dependent();
        dependent2.setCpfDep("12345678900");
        dependent2.setNomeDep("John Doe");

        assertEquals(dependent1, dependent2);
        assertEquals(dependent1.hashCode(), dependent2.hashCode());

        dependent2.setCpfDep("98765432100");
        assertNotEquals(dependent1, dependent2);
    }

    @Test
    void testDefaultConstructor() {
        Dependent dependent = new Dependent();

        assertNull(dependent.getCpfDep());
        assertNull(dependent.getNomeDep());
        assertNull(dependent.getIdadeDep());
        assertNull(dependent.getTipoSanguineo());
        assertNull(dependent.getLaudo());
        assertNull(dependent.getGeneroDep());
        assertNull(dependent.getRgDep());
        assertNull(dependent.getCpfResDep());
        assertNull(dependent.getPiTagIdDep());
        assertNull(dependent.getCpfTerDep());
        assertNull(dependent.getIdCirurgiaDep());
        assertNull(dependent.getIdScanDep());
    }

    @Test
    void testHashCodeUniqueness() {
        Dependent dependent1 = new Dependent();
        dependent1.setCpfDep("12345678900");

        Dependent dependent2 = new Dependent();
        dependent2.setCpfDep("98765432100");

        assertNotEquals(dependent1.hashCode(), dependent2.hashCode());
    }
}
