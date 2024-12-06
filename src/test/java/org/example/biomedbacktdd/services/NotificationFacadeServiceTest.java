package org.example.biomedbacktdd.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.biomedbacktdd.dto.commands.NotificationRequestCommand;
import org.example.biomedbacktdd.dto.commands.NotificationStorageCommand;
import org.example.biomedbacktdd.entities.devicestorage.DeviceStorage;
import org.example.biomedbacktdd.entities.notification.NotificationRequest;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.example.biomedbacktdd.exceptions.ServiceException;
import org.example.biomedbacktdd.repositories.interfaces.devicestorage.IDeviceStorageRepository;
import org.example.biomedbacktdd.repositories.interfaces.responsible.IResponsibleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationFacadeServiceTest {

    private NotificationRequestService notificationRequestService;
    private NotificationStorageService notificationStorageService;
    private IResponsibleRepository responsibleRepository;
    private IDeviceStorageRepository deviceStorageRepository;
    private NotificationFacadeService notificationFacadeService;

    @BeforeEach
    void setUp() {
        notificationRequestService = mock(NotificationRequestService.class);
        notificationStorageService = mock(NotificationStorageService.class);
        responsibleRepository = mock(IResponsibleRepository.class);
        deviceStorageRepository = mock(IDeviceStorageRepository.class);

        notificationFacadeService = new NotificationFacadeService(
                notificationRequestService,
                notificationStorageService,
                responsibleRepository,
                deviceStorageRepository
        );
    }

    @Test
    void testSendAndStoreNotification_Success() throws FirebaseMessagingException {
        // Dado
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setCpfResponsavel("12345678900");
        command.setCpfDependente("09876543211");
        command.setTitle("Test Title");
        command.setBody("Test Body");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        DeviceStorage device1 = new DeviceStorage();
        device1.setId(1);
        device1.setTokenDispositivo("token1");

        DeviceStorage device2 = new DeviceStorage();
        device2.setId(2);
        device2.setTokenDispositivo("token2");

        List<DeviceStorage> devices = Arrays.asList(device1, device2);

        when(responsibleRepository.findById(command.getCpfResponsavel())).thenReturn(Optional.of(responsible));
        when(deviceStorageRepository.findByResponsavel(responsible)).thenReturn(devices);

        // Quando
        notificationFacadeService.sendAndStoreNotification(command);

        // Então
        // Verificar que sendNotification foi chamado para cada dispositivo
        verify(notificationRequestService, times(2)).sendNotification(any(NotificationRequest.class));

        // Capturar os argumentos passados para sendNotification
        ArgumentCaptor<NotificationRequest> notificationRequestCaptor = ArgumentCaptor.forClass(NotificationRequest.class);
        verify(notificationRequestService, times(2)).sendNotification(notificationRequestCaptor.capture());

        List<NotificationRequest> capturedNotifications = notificationRequestCaptor.getAllValues();
        assertEquals(2, capturedNotifications.size());

        // Verificar que as notificações foram enviadas para os tokens corretos
        assertTrue(capturedNotifications.stream().anyMatch(nr -> nr.getToken().equals("token1")));
        assertTrue(capturedNotifications.stream().anyMatch(nr -> nr.getToken().equals("token2")));

        // Verificar que storeNotification foi chamado com o comando correto
        ArgumentCaptor<NotificationStorageCommand> storageCommandCaptor = ArgumentCaptor.forClass(NotificationStorageCommand.class);
        verify(notificationStorageService, times(1)).storeNotification(storageCommandCaptor.capture());

        NotificationStorageCommand capturedStorageCommand = storageCommandCaptor.getValue();
        assertEquals(command.getTitle(), capturedStorageCommand.getTitulo());
        assertEquals(command.getBody(), capturedStorageCommand.getMensagem());
        assertEquals(command.getCpfResponsavel(), capturedStorageCommand.getCpfResponsavel());
        assertEquals(command.getCpfDependente(), capturedStorageCommand.getCpfDependente());
    }

    @Test
    void testSendAndStoreNotification_ResponsibleNotFound() {
        // Dado
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setCpfResponsavel("12345678900");

        when(responsibleRepository.findById(command.getCpfResponsavel())).thenReturn(Optional.empty());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            notificationFacadeService.sendAndStoreNotification(command);
        });

        assertEquals("Responsável não encontrado com CPF: 12345678900", exception.getMessage());

        verifyNoInteractions(deviceStorageRepository);
        verifyNoInteractions(notificationRequestService);
        verifyNoInteractions(notificationStorageService);
    }

    @Test
    void testSendAndStoreNotification_NoDevicesFound() throws FirebaseMessagingException {
        // Dado
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setCpfResponsavel("12345678900");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        when(responsibleRepository.findById(command.getCpfResponsavel())).thenReturn(Optional.of(responsible));
        when(deviceStorageRepository.findByResponsavel(responsible)).thenReturn(Collections.emptyList());

        // Quando e Então
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            notificationFacadeService.sendAndStoreNotification(command);
        });

        assertEquals("Nenhum dispositivo encontrado para o responsável com CPF: 12345678900", exception.getMessage());

        verify(notificationRequestService, never()).sendNotification(any(NotificationRequest.class));
        verify(notificationStorageService, never()).storeNotification(any(NotificationStorageCommand.class));
    }

    @Test
    void testSendAndStoreNotification_FirebaseMessagingException() throws FirebaseMessagingException {
        // Dado
        NotificationRequestCommand command = new NotificationRequestCommand();
        command.setCpfResponsavel("12345678900");
        command.setCpfDependente("09876543211");
        command.setTitle("Test Title");
        command.setBody("Test Body");

        Responsible responsible = new Responsible();
        responsible.setCpfRes("12345678900");

        DeviceStorage device1 = new DeviceStorage();
        device1.setId(1);
        device1.setTokenDispositivo("token1");

        DeviceStorage device2 = new DeviceStorage();
        device2.setId(2);
        device2.setTokenDispositivo("token2");

        List<DeviceStorage> devices = Arrays.asList(device1, device2);

        when(responsibleRepository.findById(command.getCpfResponsavel())).thenReturn(Optional.of(responsible));
        when(deviceStorageRepository.findByResponsavel(responsible)).thenReturn(devices);

        // Criando um mock da FirebaseMessagingException
        FirebaseMessagingException mockException = mock(FirebaseMessagingException.class);
        when(mockException.getMessage()).thenReturn("Test exception");

        // Configurar o notificationRequestService para lançar exceção no primeiro dispositivo
        doThrow(mockException)
                .when(notificationRequestService).sendNotification(argThat(nr -> nr.getToken().equals("token1")));

        // Quando
        notificationFacadeService.sendAndStoreNotification(command);

        // Então
        // Verificar que sendNotification foi chamado para cada dispositivo
        verify(notificationRequestService, times(2)).sendNotification(any(NotificationRequest.class));

        // Verificar que storeNotification foi chamado
        verify(notificationStorageService, times(1)).storeNotification(any(NotificationStorageCommand.class));

        // Não podemos verificar os logs sem configuração adicional, então vamos pular essa parte
    }
}
