package org.example.biomedbacktdd.entities.responsible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsibleTest {

    private Responsible responsible;

    @BeforeEach
    void setUp() {
        responsible = new Responsible();
    }

    @Test
    void testGettersAndSetters() {
        responsible.setCpfRes("12345678900");
        assertEquals("12345678900", responsible.getCpfRes());

        responsible.setNomeRes("John Doe");
        assertEquals("John Doe", responsible.getNomeRes());

        responsible.setIdadeRes(40);
        assertEquals(40, responsible.getIdadeRes());

        responsible.setContato1Res("123456789");
        assertEquals("123456789", responsible.getContato1Res());

        responsible.setContato2Res("987654321");
        assertEquals("987654321", responsible.getContato2Res());

        responsible.setContato3Res("543216789");
        assertEquals("543216789", responsible.getContato3Res());

        responsible.setPlanoAssinado(1);
        assertEquals(1, responsible.getPlanoAssinado());

        responsible.setEmailRes("johndoe@example.com");
        assertEquals("johndoe@example.com", responsible.getEmailRes());

        responsible.setRgRes("MG1234567");
        assertEquals("MG1234567", responsible.getRgRes());

        responsible.setCepRes("12345678");
        assertEquals("12345678", responsible.getCepRes());

        responsible.setLogradouro("Main Street");
        assertEquals("Main Street", responsible.getLogradouro());

        responsible.setNumero(100);
        assertEquals(100, responsible.getNumero());

        responsible.setComplemento("Apto 101");
        assertEquals("Apto 101", responsible.getComplemento());

        responsible.setBairro("Centro");
        assertEquals("Centro", responsible.getBairro());

        responsible.setCidade("Cityville");
        assertEquals("Cityville", responsible.getCidade());

        responsible.setEstado("State");
        assertEquals("State", responsible.getEstado());
    }

    @Test
    void testEmptyConstructor() {
        assertNull(responsible.getCpfRes());
        assertNull(responsible.getNomeRes());
        assertNull(responsible.getIdadeRes());
        assertNull(responsible.getContato1Res());
        assertNull(responsible.getContato2Res());
        assertNull(responsible.getContato3Res());
        assertNull(responsible.getPlanoAssinado());
        assertNull(responsible.getEmailRes());
        assertNull(responsible.getRgRes());
        assertNull(responsible.getCepRes());
        assertNull(responsible.getLogradouro());
        assertNull(responsible.getNumero());
        assertNull(responsible.getComplemento());
        assertNull(responsible.getBairro());
        assertNull(responsible.getCidade());
        assertNull(responsible.getEstado());
    }

    @Test
    void testParameterizedConstructor() {
        Responsible responsible = new Responsible(
                "12345678900",
                "John Doe",
                40,
                "123456789",
                "987654321",
                "543216789",
                1,
                "johndoe@example.com",
                "MG1234567",
                "12345678",
                "Main Street",
                100,
                "Apto 101",
                "Centro",
                "Cityville",
                "State"
        );

        assertEquals("12345678900", responsible.getCpfRes());
        assertEquals("John Doe", responsible.getNomeRes());
        assertEquals(40, responsible.getIdadeRes());
        assertEquals("123456789", responsible.getContato1Res());
        assertEquals("987654321", responsible.getContato2Res());
        assertEquals("543216789", responsible.getContato3Res());
        assertEquals(1, responsible.getPlanoAssinado());
        assertEquals("johndoe@example.com", responsible.getEmailRes());
        assertEquals("MG1234567", responsible.getRgRes());
        assertEquals("12345678", responsible.getCepRes());
        assertEquals("Main Street", responsible.getLogradouro());
        assertEquals(100, responsible.getNumero());
        assertEquals("Apto 101", responsible.getComplemento());
        assertEquals("Centro", responsible.getBairro());
        assertEquals("Cityville", responsible.getCidade());
        assertEquals("State", responsible.getEstado());
    }

    @Test
    void testEqualsAndHashCode() {
        Responsible res1 = new Responsible();
        res1.setCpfRes("12345678900");
        res1.setNomeRes("John Doe");

        Responsible res2 = new Responsible();
        res2.setCpfRes("12345678900");
        res2.setNomeRes("John Doe");

        assertEquals(res1, res2);
        assertEquals(res1.hashCode(), res2.hashCode());

        res2.setCpfRes("98765432100");
        assertNotEquals(res1, res2);
        assertNotEquals(res1.hashCode(), res2.hashCode());
    }
}
