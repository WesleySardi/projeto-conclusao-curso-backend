package org.example.biomedbacktdd.services;

import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.entities.notification.NotificationStorage;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.dependent.IDependentRepository;
import org.example.biomedbacktdd.repositories.interfaces.notification.INotificationStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationStorageServiceTest {

    private INotificationStorageRepository notificationStorageRepository;
    private IResponsibleRepository responsibleRepository;
    private IDependentRepository dependentRepository;
    private NotificationStorageService notificationStorageService;

    @BeforeEach
    public void setUp() {
        notificationStorageRepository = mock(INotificationStorageRepository.class);
        responsibleRepository = mock(IResponsibleRepository.class);
        dependentRepository = mock(IDependentRepository.class);
        notificationStorageService = new NotificationStorageService(notificationStorageRepository, responsibleRepository, dependentRepository);
    }

    @Test
    void testStoreNotification_Success() {
        // Dado
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("Teste");
        command.setMensagem("Mensagem de teste");
        command.setCpfResponsavel("12345678900");
        command.setCpfDependente("09876543211");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        Dependent dependent = new Dependent();
        dependent.setCpfDep("09876543211");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel())).thenReturn(Optional.of(responsible));
        when(dependentRepository.findById(command.getCpfDependente())).thenReturn(Optional.of(dependent));
        when(notificationStorageRepository.save(any(NotificationStorage.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Quando
        NotificationStorage result = notificationStorageService.storeNotification(command);

        // Então
        assertNotNull(result);
        assertEquals("Teste", result.getTitulo());
        assertEquals("Mensagem de teste", result.getMensagem());
        assertEquals("09876543211", result.getCpfDependente());
        assertEquals(responsible, result.getResponsavel());
        assertFalse(result.getLida());
        assertNotNull(result.getDataEnvio());

        verify(notificationStorageRepository, times(1)).save(any(NotificationStorage.class));
    }

    @Test
    void testStoreNotification_ResponsibleNotFound() {
        // Dado
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("Teste");
        command.setMensagem("Mensagem de teste");
        command.setCpfResponsavel("12345678900");
        command.setCpfDependente("09876543211");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel())).thenReturn(Optional.empty());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            notificationStorageService.storeNotification(command);
        });

        assertEquals("Responsável não encontrado com CPF: 12345678900", exception.getMessage());
        verify(notificationStorageRepository, never()).save(any(NotificationStorage.class));
    }

    @Test
    void testStoreNotification_DependentNotFound() {
        // Dado
        NotificationStorageCommand command = new NotificationStorageCommand();
        command.setTitulo("Teste");
        command.setMensagem("Mensagem de teste");
        command.setCpfResponsavel("12345678900");
        command.setCpfDependente("09876543211");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        when(responsibleRepository.findResponsibleByCpf(command.getCpfResponsavel())).thenReturn(Optional.of(responsible));
        when(dependentRepository.findById(command.getCpfDependente())).thenReturn(Optional.empty());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            notificationStorageService.storeNotification(command);
        });

        assertEquals("Dependente não encontrado com CPF: 09876543211", exception.getMessage());
        verify(notificationStorageRepository, never()).save(any(NotificationStorage.class));
    }

    @Test
    void testDeleteNotification_Success() {
        // Dado
        int idNotificacao = 1;
        NotificationStorage notification = new NotificationStorage();
        notification.setIdNotificacao(idNotificacao);

        when(notificationStorageRepository.findByIdNotificacao(idNotificacao)).thenReturn(Optional.of(notification));

        // Quando
        notificationStorageService.deleteNotification(idNotificacao);

        // Então
        verify(notificationStorageRepository, times(1)).delete(notification);
    }

    @Test
    void testDeleteNotification_NotificationNotFound() {
        // Dado
        int idNotificacao = 1;

        when(notificationStorageRepository.findByIdNotificacao(idNotificacao)).thenReturn(Optional.empty());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            notificationStorageService.deleteNotification(idNotificacao);
        });

        assertEquals("Notificação não encontrada com ID: 1", exception.getMessage());
        verify(notificationStorageRepository, never()).delete(any(NotificationStorage.class));
    }

    @Test
    void testGetNotificationsByResponsavel_WithNotifications() {
        // Dado
        String cpfResponsavel = "12345678900";
        NotificationStorage notification1 = new NotificationStorage();
        NotificationStorage notification2 = new NotificationStorage();

        when(notificationStorageRepository.findByResponsavelCpfRes(cpfResponsavel))
                .thenReturn(Arrays.asList(notification1, notification2));

        // Quando
        List<NotificationStorage> notifications = notificationStorageService.getNotificationsByResponsavel(cpfResponsavel);

        // Então
        assertNotNull(notifications);
        assertEquals(2, notifications.size());
        verify(notificationStorageRepository, times(1)).findByResponsavelCpfRes(cpfResponsavel);
    }

    @Test
    void testGetNotificationsByResponsavel_NoNotifications() {
        // Dado
        String cpfResponsavel = "12345678900";

        when(notificationStorageRepository.findByResponsavelCpfRes(cpfResponsavel))
                .thenReturn(List.of());

        // Quando
        List<NotificationStorage> notifications = notificationStorageService.getNotificationsByResponsavel(cpfResponsavel);

        // Então
        assertNotNull(notifications);
        assertTrue(notifications.isEmpty());
        verify(notificationStorageRepository, times(1)).findByResponsavelCpfRes(cpfResponsavel);
    }
}
