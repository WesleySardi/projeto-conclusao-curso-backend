package org.example.biomedbacktdd.entities.devicestorage;

import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeviceStorageTest {

    private DeviceStorage deviceStorage;

    @BeforeEach
    void setUp() {
        deviceStorage = new DeviceStorage();
    }

    @Test
    void testGettersAndSetters() {
        deviceStorage.setId(1);
        assertEquals(1, deviceStorage.getId());

        deviceStorage.setTokenDispositivo("abc123");
        assertEquals("abc123", deviceStorage.getTokenDispositivo());

        Responsible responsavel = mock(Responsible.class); // Usando um mock para testar associações
        deviceStorage.setResponsavel(responsavel);
        assertEquals(responsavel, deviceStorage.getResponsavel());

        Date now = new Date();
        deviceStorage.setDataCadastro(now);
        assertEquals(now, deviceStorage.getDataCadastro());
    }

    @Test
    void testPrePersist() {
        deviceStorage.onCreate();
        assertNotNull(deviceStorage.getDataCadastro(), "dataCadastro should be initialized in onCreate method");
    }

    @Test
    void testEmptyConstructor() {
        assertNull(deviceStorage.getId());
        assertNull(deviceStorage.getTokenDispositivo());
        assertNull(deviceStorage.getResponsavel());
        assertNull(deviceStorage.getDataCadastro());
    }

    @Test
    void testAssociationWithResponsible() {
        Responsible responsavel = new Responsible();
        responsavel.setCpfRes("12345678900");

        deviceStorage.setResponsavel(responsavel);
        assertEquals("12345678900", deviceStorage.getResponsavel().getCpfRes());
    }

    @Test
    void testDefaultDataCadastroOnPersist() {
        deviceStorage.setTokenDispositivo("token123");
        deviceStorage.onCreate();
        assertNotNull(deviceStorage.getDataCadastro(), "dataCadastro should be set to the current date if not provided");

        Date manualDate = new Date();
        deviceStorage.setDataCadastro(manualDate);
        assertEquals(manualDate, deviceStorage.getDataCadastro(), "Manual dateCadastro should not be overwritten");
    }
}
