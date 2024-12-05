package org.example.biomedbacktdd.entities.notification;

import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NotificationStorageTest {

    private NotificationStorage notificationStorage;

    @BeforeEach
    void setUp() {
        notificationStorage = new NotificationStorage();
    }

    @Test
    void testGettersAndSetters() {
        notificationStorage.setIdNotificacao(1);
        assertEquals(1, notificationStorage.getIdNotificacao());

        notificationStorage.setTitulo("Test Title");
        assertEquals("Test Title", notificationStorage.getTitulo());

        notificationStorage.setMensagem("Test Message");
        assertEquals("Test Message", notificationStorage.getMensagem());

        ZonedDateTime now = ZonedDateTime.now();
        notificationStorage.setDataEnvio(now);
        assertEquals(now, notificationStorage.getDataEnvio());

        notificationStorage.setLida(true);
        assertTrue(notificationStorage.getLida());

        Responsible responsible = mock(Responsible.class);
        notificationStorage.setResponsavel(responsible);
        assertEquals(responsible, notificationStorage.getResponsavel());

        notificationStorage.setCpfDependente("12345678900");
        assertEquals("12345678900", notificationStorage.getCpfDependente());
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(0, notificationStorage.getIdNotificacao());
        assertNull(notificationStorage.getTitulo());
        assertNull(notificationStorage.getMensagem());
        assertNull(notificationStorage.getDataEnvio());
        assertNull(notificationStorage.getLida());
        assertNull(notificationStorage.getResponsavel());
        assertNull(notificationStorage.getCpfDependente());
    }

    @Test
    void testMandatoryFields() {
        notificationStorage.setTitulo("Important Title");
        notificationStorage.setMensagem("Important Message");
        notificationStorage.setDataEnvio(ZonedDateTime.now());
        notificationStorage.setLida(false);

        assertNotNull(notificationStorage.getTitulo(), "Titulo should not be null");
        assertNotNull(notificationStorage.getMensagem(), "Mensagem should not be null");
        assertNotNull(notificationStorage.getDataEnvio(), "DataEnvio should not be null");
        assertNotNull(notificationStorage.getLida(), "Lida should not be null");
    }

    @Test
    void testAssociationWithResponsible() {
        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        notificationStorage.setResponsavel(responsible);
        assertEquals("12345678900", notificationStorage.getResponsavel().getCpfRes());
    }

    @Test
    void testSetCpfDependente() {
        notificationStorage.setCpfDependente("98765432100");
        assertEquals("98765432100", notificationStorage.getCpfDependente());
    }
}
